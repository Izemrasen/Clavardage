package com.clavardage.gui;

import com.clavardage.*;
import com.clavardage.tasks.AnnouncementManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginScreenPresenter
{
	private final GuiLoginScreen guiLoginScreen;

	public LoginScreenPresenter(GuiLoginScreen guiLoginScreen)
	{
		this.guiLoginScreen = guiLoginScreen;
	}

	public void onComputeButtonClicked(String username) throws UnknownHostException
	{
		Main.setUsername(username);
		
		if (Authentication.login(username)) {
			// Display chat
			guiLoginScreen.setVisible(false);
			guiLoginScreen.dispose();
			GuiChatSystem frame = new GuiChatSystem();
			GuiChatSystem.guiChatSystem.displayActiveUsers();
			frame.display();
		} else {
			Main.setUsername("");
			guiLoginScreen.usernameAlreadyInUse(username);
		}
	}
}
