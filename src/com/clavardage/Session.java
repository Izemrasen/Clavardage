package com.clavardage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Session
{
	// Table keeping every open sessions originating from the client or the server
	// TODO: remove/destroy session when closed
	private static ArrayList<Session> sessions = new ArrayList<Session>(); // TODO: keep a single session table (remove this one or User's)
	private static ArrayList<Message<?>> messageBuffer = new ArrayList<Message<?>>(); // Buffer to keep messages received recently?

    private User remoteUser;
	// TODO: provide session status (OPEN, CLOSED, etc.)?
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
        
        this.oos.writeObject(message);
        //TODO: catch "java.net.SocketException: Broken pipe" (happens when the server closes connection while client is still connected)
    }

    public void start() throws IOException
    {
        // Initiate TCP connection
    	// TODO: correct that comment (I think TCP connection is initiated when opening stream)
    	System.out.println("<Client> Establishing connection with " + remoteUser.getUsername() + " (" + remoteUser.getIPAddr() + ":" + remoteUser.getPortNbr() + ")...");
        this.socket = new Socket(remoteUser.getIPAddr(),remoteUser.getPortNbr());
        
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
        
        // TODO: Remove session from table? Destroy session
    }
}
