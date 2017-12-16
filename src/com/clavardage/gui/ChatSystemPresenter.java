package com.clavardage.gui;

import java.io.IOException;

import com.clavardage.Session;
import com.clavardage.User;

public class ChatSystemPresenter
{
	private final GuiChatSystem guiChatSystem;

	public ChatSystemPresenter(GuiChatSystem guiChatSystem)
	{
		this.guiChatSystem = guiChatSystem;
	}

	public void onConnectButtonClicked(User user)
	{
		// Create session
		// TODO: make sure the connection isn't already established before trying to connect
		Session session = new Session(user);
		try {
			session.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: open new window for chatting
	}
}
