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
			// User u = new User(username,
			// InetAddress.getLocalHost().getHostAddress().toString(),
			// Network.MESSAGE_PORT);
			Network.broadcast(new MessageEvent(MessageEvent.Event.ALIVE, username + "is connected!"));
			guiLoginScreen.setVisible(false);
			guiLoginScreen.dispose();
			GuiChatSystem frame = new GuiChatSystem();
			frame.display();
		} else {
			Main.setUsername("");
			guiLoginScreen.usernameAlreadyInUse(username);
		}
	}
}
