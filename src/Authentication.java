import java.util.ArrayList;

public class Authentication
{
    public static boolean login(String username)
    {
        // Check username
        if (!isUsernameUnique(username))
            return false;

        // Send notification: user logged in
        MessageEvent eventLogin = new MessageEvent(MessageEvent.EventMarker.LOGIN, "");
        Network.broadcast(eventLogin);
        return true;
    }

    public static boolean changeUsername(String oldUsername, String newUsername)
    {
        // Check username
        if (!isUsernameUnique(newUsername))
            return false;

        // Send notification: user changed name
        MessageEvent eventChange = new MessageEvent(MessageEvent.EventMarker.USERNAME_CHANGED, newUsername);
        Network.broadcast(eventChange);

        // Update db (to avoid duplicates, username is not stored for each message => stored once for every conversation the user add => update that name)
        return true;
    }

    private static boolean isUsernameUnique(String username)
    {
        // Get active users
        ArrayList<User> users = Network.getActiveUsers();
        for (User user : users)
            if (user.getUsername().equals(username))
                return false;
        return true;
    }
}
