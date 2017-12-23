package com.clavardage.gui;

import java.io.IOException;

import com.clavardage.*;

import javax.print.DocFlavor;

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

	public void onSendButtonClicked(String message)
	{
		History history = User.findUser(Main.getUsername()).getHistory();
		history.load();
		MessageText m = new MessageText(message);
		m.label(Message.Direction.SENT);
		history.getMessages().clear();
		history.add(m);
		history.save();
	}

	public void onConfirmButtonClicked(String username)
	{
		Authentication.changeUsername(username);
	}
}
