package com.clavardage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Network
{
	public static final int MESSAGE_PORT = 6666;
	public static final int ANNOUNCEMENT_PORT = 6667;
	public static final long ANNOUNCEMENT_TIMEOUT = 10000; // 10s

	// Broadcast messages
	public static void broadcast(Message<?> message)
	{
		// Send message to broadcast IP address (layer 3 is preferable than layer 2 because the latter isn't necessarily available for any VPN setups)
		DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);

			message.label(Message.Direction.SENT, Main.getUsername());
			byte[] sendData = message.toDatagram();
			//TODO: check sendData not null
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), Network.ANNOUNCEMENT_PORT);
			socket.send(sendPacket);
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//ArrayList<User> users = User.getActiveUsers();
		//for (User user : users) {
		//user.getSession().sendBasic(message);
		//TODO: send message via UDP
		//}
	}
}
