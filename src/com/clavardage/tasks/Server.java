package com.clavardage.tasks;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.clavardage.Message;
import com.clavardage.MessageText;
import com.clavardage.Network;
import com.clavardage.Session;
import com.clavardage.User;

public class Server implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("Starting server...");
		ServerSocket serverSocket;
		try {
			// Initialise server socket
			serverSocket = new ServerSocket(Network.MESSAGE_PORT);
			for(;;) {
				System.out.println("<Server> Waiting for clients...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("<Server> Caught client!!! :)");

				// Launch new thread dedicated to this client
				(new Thread(new Listen(clientSocket))).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Wrapper for launching thread more easily
	public static void start()
	{
		// Launch thread listening for TCP incomes
		(new Thread(new Server())).start();
	}

	public class Listen implements Runnable
	{
		private Socket clientSocket;

		public Listen(Socket clientSocket)
		{
			this.clientSocket = clientSocket;
		}

		// TODO: remove redundancies (procedure for timestamping message, etc.) 
		@Override
		public void run()
		{
			// Open stream
			InputStream is;
			ObjectInputStream ois;
			System.out.println("<Server> Opening stream...");

			try {
				is = clientSocket.getInputStream();
				ois = new ObjectInputStream(is);
				System.out.println("<Server> Stream opened.");

				// Get ID information
				MessageText messageID = (MessageText) ois.readObject();
				System.out.println("<Server> Got message.");
				messageID.label(Message.Direction.RECEIVED);

				// Create session
				User client = new User(messageID.getContent(), clientSocket.getRemoteSocketAddress().toString(), clientSocket.getPort());
				Session session = new Session(client);
				Session.addSession(session);

				// Loop for getting next messages
				// TODO: check socket state w/ clientSocket.isClosed()?
				try {
					for (;;)
					{
						// Update message metadata and save it
						Message<?> m = (Message<?>) ois.readObject();
						System.out.println("<Server> Got message.");
						m.label(Message.Direction.RECEIVED);
						session.getRemoteUser().getHistory().add(m);
					}
				} catch (SocketTimeoutException exc) {
					// You got the timeout
					// TODO: do the same thing than EOFException catch
				} catch (EOFException exc) {
					// End of stream
					System.out.println("<Server> End of stream.");
					System.out.println("<Server> Closing stream...");
					ois.close();
					is.close();
					System.out.println("<Server> Stream closed.");

					// Close session
					System.out.println("<Server> Closing session...");
					clientSocket.close();
					System.out.println("<Server> Session closed.");
					System.out.println("<Server> History:\n" + session.getRemoteUser().getHistory().toString());

					// TODO: Remove session from table, destroy session
				} catch (IOException exc) {
					// some other I/O error: print it, log it, etc.
					exc.printStackTrace(); // for example
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
