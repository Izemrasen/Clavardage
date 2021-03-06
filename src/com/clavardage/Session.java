package com.clavardage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Session
{
	// Table keeping every open sessions originating from the client or the server
	private static ArrayList<Session> sessions = new ArrayList<Session>();

	private User remoteUser;
	private Socket socket;
	private OutputStream os; // Used for end-to-end object streaming
	private ObjectOutputStream oos;

	public Session(User remoteUser)
	{
		this.remoteUser = remoteUser;
	}

	public static ArrayList<Session> getSessions()
	{
		return sessions;
	}

	public static void addSession(Session session)
	{
		sessions.add(session);
	}

	public User getRemoteUser()
	{
		return remoteUser;
	}

	// Send message and update history
	public void send(Message<?> message) throws IOException
	{
		this.sendBasic(message);
		this.remoteUser.getHistory().add(message);
	}

	// Send message without updating history
	public void sendBasic(Message<?> message) throws IOException
	{
		System.out.println("<Client> Sending message...");
		// Update message metadata
		message.label(Message.Direction.SENT, Main.getUsername());

		try {
			this.oos.writeObject(message);
		} catch (SocketException e) {
			// Remote server closed connection while client was still connected
		}
	}

	public void start() throws IOException
	{
		// Establish TCP connection
		System.out.println("<Client> Establishing connection with " + remoteUser.getUsername() + " ("
			+ remoteUser.getIPAddr() + ":" + remoteUser.getPortNbr() + ")...");
		try {
			this.socket = new Socket(remoteUser.getIPAddr(), remoteUser.getPortNbr());
		} catch (ConnectException e) {
			// Remote user logged out. Too bad...
			return;
		}

		// Open stream
		System.out.println("<Client> Opening stream...");
		this.os = socket.getOutputStream();
		this.oos = new ObjectOutputStream(os);
		System.out.println("<Client> Stream opened.");

		// Send ID information
		sendBasic(new MessageText(Main.getUsername()));
	}

	public void stop() throws IOException
	{
		// Close socket connection
		System.out.println("<Client> Closing stream...");
		this.oos.close();
		this.os.close();
		System.out.println("<Client> Stream closed.");

		// Close session
		System.out.println("<Client> Closing session...");
		this.socket.close();
		System.out.println("<Client> Session closed.");
	}
}
