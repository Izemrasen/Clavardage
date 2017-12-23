package com.clavardage.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.clavardage.Main;
import com.clavardage.User;

public class GuiChatSystem extends JFrame
{
	private static final long serialVersionUID = 1L;
	private final ChatSystemPresenter CSpresenter;
	public static GuiChatSystem guiChatSystem;
	public static JTextPane historyPane;
	public String currentRemoteUser;

	JPanel rightPanel = new JPanel(new BorderLayout(2, 0));
	JPanel leftPanel = new JPanel((new BorderLayout(2, 0)));

	JPanel userListPanel = new JPanel(new GridLayout(100, 0));

	public GuiChatSystem()
	{
		super("Clavardage");
		this.CSpresenter = new ChatSystemPresenter(this);
		this.setLayout(new BorderLayout());
		guiChatSystem = this;

		/******** RIGHT PANEL ********/

		// Change Login
		JTextField changeLoginTextField = new JTextField(Main.getUsername());
		changeLoginTextField.setToolTipText("Insert your new login");
		changeLoginTextField.setBackground(null);
		changeLoginTextField.setBorder(null);
		changeLoginTextField
			.addActionListener(e -> this.CSpresenter.onChangeUsernameClicked(changeLoginTextField.getText()));

		// Settings Panel
		JPanel settingsPanel = new JPanel();

		settingsPanel.add(changeLoginTextField);

		// User List Panel

		JPanel userContainer = new JPanel();
		JScrollPane usersScrollPanel = new JScrollPane(userListPanel);
		usersScrollPanel.setPreferredSize(new Dimension(170, 470));
		usersScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		userContainer.add(usersScrollPanel);
		userContainer
			.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Active Users"));
		settingsPanel.setBorder(
			BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Change Username"));
		rightPanel.add(userContainer, BorderLayout.CENTER);
		rightPanel.add(settingsPanel, BorderLayout.SOUTH);
		rightPanel.setPreferredSize(new Dimension(200, 70));

		/******** LEFT PANEL ********/
		// Chat Panel
		historyPane = new JTextPane();
		historyPane.setEditable(false);
		JScrollPane chatScrollPane = new JScrollPane(historyPane);

		// Entry Panel
		JButton dataMessage = new JButton("Data");
		JButton sendButton = new JButton("Send");
		JTextField textMessage = new JTextField("enter your message ...");
		textMessage.getFont().deriveFont(Font.ITALIC);
		textMessage.setForeground(Color.gray);
		textMessage.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				JTextField textEntryMessage = ((JTextField) e.getSource());
				textEntryMessage.setText("");
				textEntryMessage.getFont().deriveFont(Font.PLAIN);
				textEntryMessage.setForeground(Color.black);
				textEntryMessage.removeMouseListener(this);
			}
		});

		JPanel containerButton = new JPanel();
		containerButton.setLayout(new GridLayout(0, 2));
		JPanel entryPane = new JPanel();
		entryPane.setLayout(new BorderLayout(0, 2));
		entryPane.add(textMessage, BorderLayout.CENTER);
		entryPane.add(containerButton, BorderLayout.EAST);
		sendButton.addActionListener(
			e -> this.CSpresenter.onSendButtonClicked(textMessage.getText(), historyPane, textMessage));

		containerButton.add(dataMessage);
		containerButton.add(sendButton);
		entryPane.setPreferredSize(new Dimension(20, 40));

		leftPanel
			.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chat session"));
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
		// Remove buttons associated to users
		this.userListPanel.removeAll();

		// Display buttons again
		synchronized (User.getUsers()) {
			for (User user : User.getUsers()) {
				JButton connectButton = new JButton(user.getUsername());
				connectButton.setPreferredSize(new Dimension(160, 25));
				this.userListPanel.add(connectButton);
				connectButton.addActionListener(e -> this.CSpresenter.onConnectButtonClicked(connectButton.getText()));
			}
		}
		this.userListPanel.revalidate();
		this.userListPanel.repaint();
	}

	public void display()
	{
		this.pack();
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
