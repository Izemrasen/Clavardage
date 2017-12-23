package com.clavardage.tasks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.clavardage.Main;
import com.clavardage.MessageEvent;
import com.clavardage.MessageEvent.Event;
import com.clavardage.Network;
import com.clavardage.User;

public class AnnouncementManager
{
	public static class Talk implements Runnable
	{
		@Override
		public void run()
		{
			for (;;) {
				// Send announcement
				Network.broadcast(new MessageEvent(MessageEvent.Event.ALIVE, ""));

				// Sleep
				try {
					Thread.sleep(Network.ANNOUNCEMENT_TIMEOUT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public static void start()
		{
			(new Thread(new Talk())).start();
		}
	}

	public static class Listen implements Runnable
	{
		@Override
		public void run()
		{
			DatagramSocket socket;
			try {
				socket = new DatagramSocket(Network.ANNOUNCEMENT_PORT, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);

				for (;;) {
					// Receive packet
					byte[] datagram = new byte[128];
					DatagramPacket packet = new DatagramPacket(datagram, datagram.length);
					socket.receive(packet);

					/*
					 * System.out.print("<<<<<ANNOUNCEMENT: "); for (byte b : datagram) {
					 * System.out.print(Integer.toHexString(b) + " "); }
					 */

					// Parse packet (cf. packet layout in the wiki)
					int portNbr = datagram[0] * 256 + datagram[1];
					Event event = MessageEvent.events.get(datagram[2]);
					String senderName = "", content = "";
					int i;
					for (i = 3; datagram[i] != 0x0; i++)
						senderName += (char) datagram[i];
					for (i++; i < datagram.length; i++) {
						if (datagram[i] == 0x0)
							break;
						content += (char) datagram[i];
					}

					// System.out.println("\n " + senderName + " (" + event.toString() + ") " +
					// content);

					User user = new User(senderName, packet.getAddress().getHostAddress(), portNbr);

					if (event == Event.ALIVE) {
						if (Main.getUsername().equals(user.getUsername())) {
							// TODO: resolve conflicts (cast the dice)
						} else {
							// Update user list
							User.addUser(user);
						}
					} else if (event == Event.USERNAME_CHANGED) {
						String newUsername = content;
						if (Main.getUsername().equals(newUsername)) {
							// TODO: resolve conflicts (cast the dice)
						} else {
							// Update user list
							User newUser = User.findUser(newUsername);
							if (newUser == null) {
								User.addUser(user);
							} else {
								User.getUsers().remove(newUser);
								User.addUser(user);
								user.setUsername(newUsername);
							}
						}
					} else if (event == Event.AM_I_UNIQUE) {
						if (Main.getUsername().equals(user.getUsername()) && Main.isConnected())
							Network.sendDatagram(new MessageEvent(MessageEvent.Event.ALIVE, ""), user.getIPAddr());
					}
				}

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static void start()
		{
			(new Thread(new Listen())).start();
		}
	}
}
