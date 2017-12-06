package com.clavardage;
import java.util.ArrayList;

public class User
{
    private String username;
    private String IPAddr;
    private int portNbr;
    private Session session;
    private History history;
    
    // TODO: avoid duplicates (single tuple of {username, IPAddr, portNbr}) -> hash table? collection?
    private static ArrayList<User> activeUsers = new ArrayList<User>();

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
    	return activeUsers;
        //users.add(new User("froufrou", "127.0.0.1", 6666));
    }
    
    public static void addActiveUser(User user)
    {
    	// TODO: avoid duplicates
    	activeUsers.add(user);
    }
    
    // Use watcher (thread processing table checking periodically) to make users expire (store timestamp in table)
    public static void removeActiveUser(User user)
    {
    	
    }
}
