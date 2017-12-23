package com.clavardage.gui;

import java.io.IOException;

import com.clavardage.Authentication;
import com.clavardage.Main;
import com.clavardage.Session;
import com.clavardage.User;

public class ChatSystemPresenter
{
	private final GuiChatSystem guiChatSystem;

	public ChatSystemPresenter(GuiChatSystem guiChatSystem)
	{
		this.guiChatSystem = guiChatSystem;
	}

	public void onConnectButtonClicked(String username)
	{
		// Create session
		// TODO: make sure the connection isn't already established before trying to
		// connect
		User u = User.findUser(username);
		Session session = new Session(u);
		try {
			session.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO: open new window for chatting
	}

	public void onRefreshButtonClicked()
	{
		// user.updateList();
		this.guiChatSystem.displayActiveUsers();
	}

	public void onConfirmButtonClicked(String username)
	{
		Main.setUsername(username);
		Authentication.changeUsername(username);
	}
}
