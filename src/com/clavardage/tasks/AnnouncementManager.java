package com.clavardage.tasks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import com.clavardage.Main;
import com.clavardage.MessageEvent;
import com.clavardage.MessageEvent.Event;
import com.clavardage.Network;
import com.clavardage.User;

public class AnnouncementManager
{
	// TODO: Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)

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
			// TODO: move code to Network?
			DatagramSocket socket;
			try {
				socket = new DatagramSocket(Network.ANNOUNCEMENT_PORT, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);

				for (;;) {
					// Receive packet
					byte[] datagram = new byte[128];
					DatagramPacket packet = new DatagramPacket(datagram, datagram.length);
					socket.receive(packet);

					// TODO: launch new thread from here to prevent announcement losses?
					System.out.print("<<<<<ANNOUNCEMENT: ");
					for (byte b : datagram) {
						System.out.print(Integer.toHexString(b) + " ");
					}

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

					System.out.println("\n   " + senderName + " (" + event.toString() + ")   " + content);

					User user = new User(senderName, packet.getAddress().getHostAddress(), portNbr);

					// TODO: Forge MessageEvent packet for archiving?
					switch (event) {
					case ALIVE:
						// Update user list
						User.addUser(user);
						
						// Resolve username conflicts (quite unlikely but it is cool)
						if (Main.getUsername().equals(user.getUsername())) {
							// TODO: resolve conflicts (cast the dice)
						}
						break;
						
					case AM_I_UNIQUE:
						if (Main.getUsername().equals(user.getUsername()))
							Network.sendDatagram(new MessageEvent(MessageEvent.Event.ALIVE, ""), user.getIPAddr());
						break;
						
					case USERNAME_CHANGED:
						// Change username
						User u = User.findUser(user.getUsername());
						u.setUsername(content);
						u.setDateAlive(new Date());
						break;
						
					default:
						break;
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
