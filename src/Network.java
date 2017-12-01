public class Network
{
    public static void sendMessage(Message message)
    {

    }

    public static void notifyUsers(Message message)
    {
        // Broadcast, etc.
        //getActiveUsers()
    }

    public static String[] getActiveUsers()
    {
        // Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)
    }

    // Idea: send messages as objects
    public static void startSession()
    {
        // Create session
        // Initiate socket connection
    }

    public static void stopSession()
    {
        // Close socket connection
        // Destroy session
    }

    public static void startListening()
    {

    }
}
