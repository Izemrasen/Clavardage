package com.clavardage.tasks;

import java.util.ArrayList;
import java.util.Date;

import com.clavardage.Network;
import com.clavardage.User;
import com.clavardage.gui.GuiChatSystem;
import com.clavardage.tasks.AnnouncementManager.Listen;

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

				// Sleep 100ms
				try {
					Thread.sleep(100);
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
			synchronized (activeUsers) {
				try {
					for (User user : activeUsers) {
						if (new Date().getTime() - user.getDateAlive().getTime() > Network.ANNOUNCEMENT_TIMEOUT + 1000) {
							// Remove user from table
							// TODO: synchronize it (ConcurrentModificationException when the list is modified from another thread)
							activeUsers.remove(user);
							
							// Update list (GUI)
							if (GuiChatSystem.guiChatSystem != null)
								GuiChatSystem.guiChatSystem.displayActiveUsers();
						}
					}
				} catch (Exception e) {
					// NOP NOP NOP
				}
			}
		}
	}
	
	public static void start()
	{
		(new Thread(new UserList())).start();
	}
}
