package com.clavardage;

import com.clavardage.gui.GuiLoginScreen;
import com.clavardage.tasks.*;

public abstract class Main
{
	// Name of the user running the client
	private static String username = "";
	private static boolean connected = false;

	public static void main(String[] args)
	{
		// Start TCP server
		Server.start();
		// Start listening for announcements
		AnnouncementManager.Listen.start();

		// Start userlist watcher
		Watcher.start();

		GuiLoginScreen frame = new GuiLoginScreen();
		frame.display();

		// Sleep (prevent closing threads too soon)
		for (;;) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		Main.username = username;
	}

	public static boolean isConnected()
	{
		return connected;
	}

	public static void setConnected(boolean connected)
	{
		Main.connected = connected;
	}
}
