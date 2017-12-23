package com.clavardage.tasks;

import java.util.ArrayList;
import java.util.Date;

import com.clavardage.Network;
import com.clavardage.User;

public class Watcher // or Timer
{
	// Thread for processing table checking periodically to make users expire
	public static class UserList implements Runnable
	{
		@Override
		public void run()
		{
			for (;;) {
				updateList();

				// Sleep 1s
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public static void updateList()
		{
			// Check list of active users
			ArrayList<User> activeUsers= User.getUsers();
			for (User user : activeUsers) {
				if (new Date().getTime() - user.getDateAlive().getTime() > Network.ANNOUNCEMENT_TIMEOUT + 1000) {
					// Remove user from table
					activeUsers.remove(user);
				}
			}
		}
	}
}
