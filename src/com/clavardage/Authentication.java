package com.clavardage;
import java.util.ArrayList;

import com.clavardage.tasks.AnnouncementManager;

public class Authentication
{
	public static boolean login(String username)
	{
		// Ask about username unicity
		Network.broadcast(new MessageEvent(MessageEvent.Event.AM_I_UNIQUE, ""));

		// Waiting for replies
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Check username
		if (!isUsernameUnique(username))
			return false;

		// Start talking
		AnnouncementManager.Talk.start();

		return true;
	}

	public static boolean changeUsername(String newUsername)
	{
		// Check username
		if (!isUsernameUnique(newUsername))
			return false;

		// Send broadcast event "username changed"
		Network.broadcast(new MessageEvent(MessageEvent.Event.USERNAME_CHANGED, newUsername));

		Main.setUsername(newUsername);

		return true;
	}

	private static boolean isUsernameUnique(String username)
	{
		// Get active users
		// TODO: send announcement and wait for answers
		ArrayList<User> users = User.getUsers();
		for (User user : users)
			if (user.getUsername().equals(username))
				return false;
		return true;
	}
}
