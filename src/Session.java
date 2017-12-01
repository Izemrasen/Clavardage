import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Session
{
    private History history;
    private User remoteUser;
    // Add local port?

    private Socket socket;
    private OutputStream os;
    private ObjectOutputStream oos;

    public Session(User remoteUser)
    {
        this.remoteUser = remoteUser;
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
        this.oos.writeObject(message);
    }

    public void start() throws IOException
    {
        // Create session
        // Initiate socket connection
        this.socket = new Socket(remoteUser.getIPAddr(),remoteUser.getPortNbr());
        this.os = socket.getOutputStream();
        this.oos = new ObjectOutputStream(os);
    }

    public void stop() throws IOException
    {
        // Close socket connection
        // Destroy session
        this.oos.close();
        this.os.close();
        this.socket.close();
    }
}
