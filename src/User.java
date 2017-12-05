import java.util.ArrayList;

public class User
{
    private String username;
    private String IPAddr;
    private int portNbr;
    private Session session;
    private History history;

    public User(String username, String IPAddr, int portNbr)
    {
        this.username = username;
        this.IPAddr = IPAddr;
        this.portNbr = portNbr;
        this.session = new Session(this);
        this.history = new History(this);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getIPAddr()
    {
        return IPAddr;
    }

    public int getPortNbr()
    {
        return portNbr;
    }
    
	public Session getSession()
	{
		return session;
	}
	
    public History getHistory()
    {
        return history;
    }
    
    public static ArrayList<User> getActiveUsers()
    {
        // Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)
        ArrayList<User> users = new ArrayList<>();
        //users.add(new User("froufrou", "127.0.0.1", 6666));
        return users;
    }
}
