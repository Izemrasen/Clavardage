import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

public class Server implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("Starting server...");
		
		// TODO: infinite loop
		// TODO: create new thread for each new session
		// TODO: special class for received messages? (avoid boolean "received")
		// TODO: remove redundancies (procedure for timestamping message, etc.) 
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(6666);
			System.out.println("<Server> Waiting for clients...");
			Socket clientSocket = serverSocket.accept();
			
			System.out.println("<Server> Caught client!!! :)");
			
			// Open stream
			InputStream is;
			ObjectInputStream ois;
			System.out.println("<Server> Opening stream...");
		    is = clientSocket.getInputStream();
		    ois = new ObjectInputStream(is);
		    System.out.println("<Server> Stream opened.");
			
			// Get ID information
		    MessageText messageID = (MessageText) ois.readObject();
		    System.out.println("<Server> Got message.");
		    messageID.setDateReceived(new Date());
		    messageID.setReceived(true);
		    User client = new User(messageID.getContent(), clientSocket.getRemoteSocketAddress().toString(), clientSocket.getPort());
            Session session = new Session(client);
            Session.addSession(session);
            session.getHistory().addMessage(messageID);
            
            // Update session
		    //while (!(clientSocket.isClosed())) {
            try {
	            for (;;)
	            {
		            Message m = (Message) ois.readObject();
				    System.out.println("<Server> Got message.");
				    m.setDateReceived(new Date());
		            m.setReceived(true);
		            session.getHistory().addMessage(m);
			    }
            } catch (SocketTimeoutException exc) {
                // you got the timeout
            } catch (EOFException exc) {
                // end of stream
            	System.out.println("<Server> End of stream.");
            	System.out.println("<Server> Closing session...");
            	ois.close();
    	        is.close();
            	System.out.println("<Server> Session closed.");
     	        System.out.println("<Server> History:\n" + session.getHistory().toString());
     	        
     	       //TODO: Destroy session
     	        
            } catch (IOException exc) {
                // some other I/O error: print it, log it, etc.
                exc.printStackTrace(); // for example
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
