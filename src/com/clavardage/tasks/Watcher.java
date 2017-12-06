package com.clavardage.tasks;

import java.util.ArrayList;

public class Watcher // or Timer
{
	public class UserList implements Runnable
	{
		private static final long timeout = 10000; // 10s
		
		@Override
		public void run()
		{
			for (;;) {
				// Check list of active users
				//ArrayList<User> activeUsers= User.getActiveUsers();
				/*for (User user : activeUsers) {
					if (date now - user.dateAdded > timeout) {
						// Remove user from table
					}
				}*/
				
				// Sleep 1s
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
