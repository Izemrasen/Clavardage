package com.clavardage;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

import com.clavardage.tasks.*;

public abstract class Main
{
	// Name of the user running the client
	private static String username = "perlimpinpin";
	
    public static void main(String[] args)
    {
    	// TODO: use lib to parse args
    	// e.g. org.apache.commons.cli.* (cf. 3MIC Graphes > PbCovoiturage.java)
    	if (args.length > 0 && args[0].equals("s")) {
    		// Start server
    		Server.start();
    	}
    	
    	else {
    		// Unit testing
	        /*User u = new User("test", "127.0.0.1", Network.PORT_MESSAGES);
	        Session s = new Session(u);
	        try {
				s.start();
				/*s.send(new MessageText("AH !!!"));
				s.send(new MessageText("Comment est votre blanquette ?"));
				
				Scanner reader = new Scanner(System.in);
				String line;
				while (reader.hasNextLine() && !((line = reader.nextLine()).isEmpty())) {
					s.send(new MessageText(line));
				}
				reader.close();
				s.stop();
				
		        System.out.println("<Client> History:\n" + s.getHistory().toString());
		        DISCOVER_FUIFSERVER_REQUEST"
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    		
    		// Test announcements
    		AnnouncementManager.start();
    		//Network.broadcast(null);
	    	
	        // Sleep
	        try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        exit();

	        /*GuiLoginScreen frame = new GuiLoginScreen();
	        frame.display();*/
    	}
    }

    // Method for exiting properly
    public static void exit()
    {
    	// No point in announcing "LOGOUT" (the remote user knows whenever TCP connection is closed, and he doesn't need to know that a user logs out)
        //Network.broadcast(new MessageEvent(MessageEvent.Event.LOGOUT, ""));
        System.out.println("Exiting...");
        System.exit(0);
    }

	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		Main.username = username;
	}
}