import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Session
{
	private static ArrayList<Session> sessions = new ArrayList<Session>();
	
    private History history;
    private User remoteUser;
    // TODO: Add local port?

    private Socket socket;
	private OutputStream os;
	private ObjectOutputStream oos;

    public Session(User remoteUser)
    {
        this.remoteUser = remoteUser;
        this.history = new History(remoteUser);
    }
    
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	public static void addSession(Session session)
	{
		sessions.add(session);
	}
	
    public History getHistory()
    {
        return history;
    }

    public User getRemoteUser()
    {
        return remoteUser;
    }

    public void send(Message message) throws IOException
    {
        System.out.println("<Client> Sending message...");
        message.setDateSent(new Date());
        message.setReceived(false);
        this.oos.writeObject(message);
        this.history.addMessage(message);
        
    }

    public void start() throws IOException, InterruptedException
    {
        // Create session
        // Initiate socket connection
    	System.out.println("<Client> Establishing connection with " + remoteUser.getUsername() + " (" + remoteUser.getIPAddr() + ":" + remoteUser.getPortNbr() + ")...");
        this.socket = new Socket(remoteUser.getIPAddr(),remoteUser.getPortNbr());
        
    	// Open stream
        System.out.println("<Client> Opening stream...");
        this.os = socket.getOutputStream();
        this.oos = new ObjectOutputStream(os);
        System.out.println("<Client> Stream opened.");
        
        // Send ID information
        send(new MessageText(Main.getUsername()));
        
        //TODO: special message for ID
    }

    public void stop() throws IOException
    {
        // Close socket connection
        System.out.println("<Client> Closing stream...");
        this.oos.close();
        this.os.close();
        System.out.println("<Client> Stream closed.");
        
        // Destroy session
    	System.out.println("<Client> Closing session...");
        this.socket.close();
        System.out.println("<Client> Session closed.");
    }
}
