import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public abstract class Main
{
	private static String username = "perlimpinpin";
	
    public static void main(String[] args)
    {
    	// TODO: use lib to parse args
    	// e.g. org.apache.commons.cli.* (cf. 3MIC Graphes > PbCovoiturage.java)
    	if (args.length > 0 && args[0].equals("s")) {
    		Network.startServer();
    	}
    	
    	else {
	        User u = new User("test", "127.0.0.1", 6666);
	        Session s = new Session(u);
	        try {
				s.start();
				/*s.send(new MessageText("AH !!!"));
				s.send(new MessageText("Comment est votre blanquette ?"));*/
				
				Scanner reader = new Scanner(System.in);
				String line;
				while (reader.hasNextLine() && !((line = reader.nextLine()).isEmpty())) {
					s.send(new MessageText(line));
				}
				s.stop();
				
		        System.out.println("<Client> History:\n" + s.getHistory().toString());
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	        exit();

	        /*GuiLoginScreen frame = new GuiLoginScreen();
	        frame.display();*/
    	}
    }

    public static void exit()
    {
        Network.broadcast(new MessageEvent(MessageEvent.EventMarker.LOGOUT, ""));
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