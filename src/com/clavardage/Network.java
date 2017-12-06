package com.clavardage;
public class Network
{
	// Broadcast messages
    public static void broadcast(Message<?> message)
    {
    	// Send message to broadcast IP address (layer 3 is preferable than layer 2 because the latter isn't necessarily available for any VPN setups)
    	// 
        //ArrayList<User> users = User.getActiveUsers();
        //for (User user : users) {
        	//user.getSession().sendBasic(message);
    		//TODO: send message via UDP
        //}
    }
}
