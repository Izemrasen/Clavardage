import java.util.ArrayList;

public class Network
{
    public static void broadcast(Message message)
    {
        // Send events LOGIN, LOGOUT, USERNAME_CHANGED, etc.
        // Add header: username
        //getActiveUsers()
    }

    public static ArrayList<User> getActiveUsers()
    {
        // Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("froufrou", "127.0.0.1", 6666));
        return users;
    }

    public static void startListening()
    {
        // Launch thread for listening for TCP incomes
        //ServerSocket...
        // if connection received -> new Session()
        /*this.is = socket.getInputStream();
        this.ois = new ObjectInputStream(is);
        while (connection alive) {
            Message m = (Message) ois.readObject();
        }
        ois.close();
        is.close();
        Destroy session*/
    }
}
