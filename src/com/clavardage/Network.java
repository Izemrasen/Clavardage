package com.clavardage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Network
{
	public static final int PORT_MESSAGES = 6666;
	public static final int PORT_ANNOUNCEMENTS = 6667;
	public static final long TIMEOUT_ANNOUNCEMENT = 10000; // 10s
	
	// Broadcast messages
    public static void broadcast(Message<?> message)
    {
    	// Send message to broadcast IP address (layer 3 is preferable than layer 2 because the latter isn't necessarily available for any VPN setups)
    	DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);
			byte[] sendData = message.toString().getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), Network.PORT_ANNOUNCEMENTS);
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
