package com.clavardage.gui;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.net.UnknownHostException;
import javax.swing.*;

public class GuiLoginScreen extends JFrame
{

	private final LoginScreenPresenter LSpresenter;

	public GuiLoginScreen()
	{
		super("Clavardage");
		this.LSpresenter = new LoginScreenPresenter(this);

		// Login area
		JLabel loginLabel = new JLabel("Login");
		JTextField loginTextField = new JTextField(15);
		loginLabel.setLabelFor(loginTextField);
		loginTextField.setToolTipText("Insert your login");

		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(loginLabel);

		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(loginTextField);

		// Button Connect
		JButton computeButton = new JButton("Connect");
		computeButton.addActionListener(e -> {
			try {
				this.LSpresenter.onComputeButtonClicked(loginTextField.getText());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		});

		// Main container
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 50, 20));
		contentPane.add(labelPane, WEST);
		contentPane.add(fieldPane, EAST);
		contentPane.add(computeButton, EAST);

		loginTextField.requestFocus();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.getRootPane().setDefaultButton(computeButton);
	}

	public void usernameAlreadyInUse(String username)
	{
		showMessageDialog(null, "'" + username + "' is already in use, please choose another username");
	}

	public void display()
	{
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
