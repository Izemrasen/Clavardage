package com.clavardage;

import java.io.ByteArrayOutputStream;
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

	// Send messages (UDP datagrams)
	public static void sendDatagram(Message<?> message, String IPAddr)
	{
		DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);

			// Add metadata to packet
			message.label(Message.Direction.SENT, Main.getUsername());
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			outputStream.write(MESSAGE_PORT >> 8 & 0xFF);
			outputStream.write(MESSAGE_PORT & 0xFF);
			outputStream.write(message.toDatagram());
			byte[] datagram = outputStream.toByteArray();

			if (datagram == null)
				return;

			// Send packet
			DatagramPacket packet =
				new DatagramPacket(datagram, datagram.length, InetAddress.getByName(IPAddr), Network.ANNOUNCEMENT_PORT);
			socket.send(packet);
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Broadcast messages
	public static void broadcast(Message<?> message)
	{
		sendDatagram(message, "255.255.255.255");
	}
}
