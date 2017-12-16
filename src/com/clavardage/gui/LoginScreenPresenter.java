package com.clavardage.gui;

import com.clavardage.Authentication;
import com.clavardage.Main;

public class LoginScreenPresenter
{
	private final GuiLoginScreen guiLoginScreen;

	public LoginScreenPresenter(GuiLoginScreen guiLoginScreen)
	{
		this.guiLoginScreen = guiLoginScreen;
	}

	public void onComputeButtonClicked(String username)
	{
		Main.setUsername(username);

		if (Authentication.login(username)) {
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
