import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("Starting server...");
		
		// TODO: create new thread for each new session
		ServerSocket serverSocket;
		try {
			// Initialise server socket
			serverSocket = new ServerSocket(6666);
			for(;;) {
				System.out.println("<Server> Waiting for clients...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("<Server> Caught client!!! :)");
				
				// Launch new thread dedicated to this client
				(new Thread(new ServerSession(clientSocket))).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void start()
    {
        // Launch thread listening for TCP incomes
    	(new Thread(new Server())).start();
    }

}
