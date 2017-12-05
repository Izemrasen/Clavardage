import java.io.IOException;
import java.util.ArrayList;

public class Network
{
	// Send messages to every active users
    public static void broadcast(Message<?> message)
    {
        ArrayList<User> users = User.getActiveUsers();
        for (User user : users) {
        	//user.getSession().sendBasic(message);
    		//TODO: send message via UDP
        }
    }
}
