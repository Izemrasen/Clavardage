package com.clavardage.tasks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.clavardage.Main;
import com.clavardage.MessageText;
import com.clavardage.Network;

public class AnnouncementManager implements Runnable
{
    // TODO: Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)
	// TODO: choose standard port (6666/6667?)

	@Override
	public void run()
	{
		// Start threads dealing with announcements (talk/listen)
		(new Thread(new Talk())).start();
		(new Thread(new Listen())).start();
	}
	
	public class Talk implements Runnable
	{
		@Override
		public void run()
		{
			for (;;) {
				// Send announcement
				MessageText message = new MessageText(Main.getUsername());
				Network.broadcast(message);
				
				// Sleep
				try {
					Thread.sleep(Network.TIMEOUT_ANNOUNCEMENT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Listen implements Runnable
	{
		@Override
		public void run()
		{
			// TODO: move to Network?
			DatagramSocket socket;
			try {
				socket = new DatagramSocket(Network.PORT_ANNOUNCEMENTS, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);
				byte[] recvBuf = new byte[15000];
				DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
				
				for (;;) {
					socket.receive(packet);
					String message = new String(packet.getData()).trim();
					System.out.println("<<<<<ANNOUNCEMENT: " + message);
					
					// TODO: Update user list (username, IP @, port number)
					//User.addActiveUser(user);
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
	}
	
	public static void start()
	{
		(new Thread(new AnnouncementManager())).start();
	}
}
