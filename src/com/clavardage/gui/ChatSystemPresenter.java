package com.clavardage.gui;

import java.io.IOException;

import com.clavardage.*;

import javax.print.DocFlavor;
import javax.swing.JTextPane;

public class ChatSystemPresenter
{
	private final GuiChatSystem guiChatSystem;

	public ChatSystemPresenter(GuiChatSystem guiChatSystem)
	{
		this.guiChatSystem = guiChatSystem;
	}

	public void onConnectButtonClicked(String username)
	{
		guiChatSystem.currentRemoteUser = username;
		
		// Create session
		// TODO: make sure the connection isn't already established before trying to
		// connect
		User u = User.findUser(username);
		History h = u.getHistory();
		h.load();
		GuiChatSystem.historyPane.setText(h.toString());
		
		Session session = u.getSession();
		// TODO: Check session is already started
		try {
			session.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO: open new window for chatting
	}

	public void onSendButtonClicked(String message, JTextPane historyPane)
	{
		// TODO: provide remote user
		User remoteUser = User.findUser(guiChatSystem.currentRemoteUser);
		History history = remoteUser.getHistory();
		
		Session session = remoteUser.getSession();
		if (session == null)
			session = new Session(remoteUser);
		// TODO: Check session is already started
		try {
			session.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MessageText m = new MessageText(message);
		try {
			session.send(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Update history (do not confuse total history with session history!)
		history.add(m);
		History sessionHistory = new History(remoteUser);
		sessionHistory.add(m);
		sessionHistory.save(); // save() appends messages to DB, that's why session history contains only the last message
		
		// Update chat panel
		historyPane.setText(history.toString());
		
		
		
		// This is beeeeeed
		/*History history = User.findUser(Main.getUsername()).getHistory();
		history.load();
		MessageText m = new MessageText(message);
		m.label(Message.Direction.SENT);
		history.getMessages().clear();
		history.add(m);
		history.save();*/
	}

	public void onConfirmButtonClicked(String username)
	{
		Authentication.changeUsername(username);
	}
}
