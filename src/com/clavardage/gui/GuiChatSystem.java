package com.clavardage.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.clavardage.Main;
import com.clavardage.User;

public class GuiChatSystem extends JFrame
{
	private final ChatSystemPresenter CSpresenter;

    JPanel rightPanel = new JPanel(new GridLayout(2, 0));
    JPanel leftPanel = new JPanel((new BorderLayout(2,0)));

    JPanel userListPanel = new JPanel(new GridLayout(100, 0));

	public GuiChatSystem()
	{
		super("Clavardage");
		this.CSpresenter = new ChatSystemPresenter(this);
		this.setLayout(new BorderLayout());

		/******** RIGHT PANEL ********/

        // Change Login
        JLabel changeLoginLabel = new JLabel("Change your Username :");
        JTextField changeLoginTextField = new JTextField(15);
        changeLoginLabel.setLabelFor(changeLoginTextField);
        changeLoginTextField.setToolTipText("Insert your login");

        // Button OK
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setSize(10, 10);
        confirmButton.addActionListener(e ->
                this.CSpresenter.onConfirmButtonClicked(changeLoginTextField.getText()));


        // Settings Panel
        JPanel settingsPanel = new JPanel(new GridLayout(4, 0));
        settingsPanel.add(changeLoginLabel);
        settingsPanel.add(changeLoginTextField);
        settingsPanel.add(confirmButton);

		// User List Panel

		JPanel userContainer = new JPanel(new BorderLayout(2, 0));
		JButton buadzaz = new JButton("mama");
		buadzaz.setSize(20,20);
		userListPanel.validate();
        JScrollPane usersScrollPanel = new JScrollPane(userListPanel);

		// Refresh Button
		JButton refreshButton = new JButton("Refresh");

		refreshButton.setPreferredSize(new Dimension(20, 20));
		refreshButton.addActionListener(e ->
				this.CSpresenter.onRefreshButtonClicked());

		userContainer.add(usersScrollPanel, BorderLayout.CENTER);
		userContainer.add(refreshButton, BorderLayout.SOUTH);

		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Users"));
		rightPanel.add(userContainer);
		rightPanel.add(settingsPanel);

		displayActiveUsers();

        /******** LEFT PANEL ********/
		// Chat Panel
		JTextPane chatTextPane = new JTextPane();
		JScrollPane chatScrollPane = new JScrollPane();
		chatScrollPane.add(chatTextPane);
		// Entry Panel
		JButton dataMessage = new JButton("Data");
		JButton sendButton = new JButton("Send");
		JTextField textMessage = new JTextField("enter your message ...");
		textMessage.getFont().deriveFont(Font.ITALIC);
		textMessage.setForeground(Color.gray);
		textMessage.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField textEntryMessage = ((JTextField)e.getSource());
				textEntryMessage.setText("");
				textEntryMessage.getFont().deriveFont(Font.PLAIN);
				textEntryMessage.setForeground(Color.black);
				textEntryMessage.removeMouseListener(this);
			}
		});
		JPanel containerButton = new JPanel();
		containerButton.setLayout(new GridLayout(0,2));
		JPanel entryPane = new JPanel();
		entryPane.setLayout(new BorderLayout(0,2));
		entryPane.add(textMessage, BorderLayout.CENTER);
		entryPane.add(containerButton, BorderLayout.EAST);

		containerButton.add(dataMessage);
		containerButton.add(sendButton);
		entryPane.setPreferredSize(new Dimension(20,40));


		leftPanel
			.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chat Session"));
		leftPanel.add(chatScrollPane, BorderLayout.CENTER);
		leftPanel.add(entryPane, BorderLayout.SOUTH);


		/******** MAIN PANEL ********/
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BorderLayout());
		this.setContentPane(panelContainer);
		panelContainer.add(leftPanel, BorderLayout.CENTER);
		panelContainer.add(rightPanel, BorderLayout.EAST);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void displayActiveUsers()
	{
		// TODO: make it less ugly
		this.userListPanel.removeAll();

		for (User user : User.findUser(Main.getUsername()).getUsers()) {
			JButton connectButton = new JButton(user.getUsername());
			this.userListPanel.add(connectButton);
            this.userListPanel.validate();
			this.userListPanel.repaint();
			connectButton.addActionListener(e -> this.CSpresenter.onConnectButtonClicked(connectButton.getText()));
		}
	}

	public void displaySession(){

    }

	public void display()
	{
		this.pack();
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
