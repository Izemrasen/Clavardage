package com.clavardage;

import java.util.ArrayList;
import java.util.Date;

import com.clavardage.gui.GuiChatSystem;
import com.clavardage.tasks.Watcher;

public class User
{
	private String username;
	private String IPAddr;
	private int portNbr;
	private Session session;
	private History history;
	private Date dateAlive;
	private static ArrayList<User> users = new ArrayList<User>();

	public User(String username, String IPAddr, int portNbr)
	{
		this.username = username;
		this.IPAddr = IPAddr;
		this.portNbr = portNbr;
		this.session = new Session(this);
		this.history = new History(this);
		this.dateAlive = new Date();
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

	public Date getDateAlive()
	{
		return dateAlive;
	}

	public void setDateAlive(Date dateAlive)
	{
		this.dateAlive = dateAlive;
	}

	public static ArrayList<User> getUsers()
	{
		return users;
	}

	public static void addUser(User newUser)
	{
		// Add user by avoiding duplicates (most recent entries prevail)
		User user = findUser(newUser.getUsername());
		if (user == null) {
			synchronized (users) {
				users.add(newUser);
			}

			// Update list (GUI)
			if (GuiChatSystem.guiChatSystem != null)
				GuiChatSystem.guiChatSystem.displayActiveUsers();
		} else {
			user.IPAddr = newUser.IPAddr;
			user.portNbr = newUser.portNbr;
			user.dateAlive = newUser.dateAlive;
		}
	}

	public synchronized static User findUser(String username)
	{
		synchronized (users) {
			for (User user : users) {
				if (user.getUsername().equals(username))
					return user;
			}
		}
		return null;
	}

	// Update list of active users
	public static void updateList()
	{
		Watcher.UserList.updateList();
	}
}
