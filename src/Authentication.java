public class Authentication
{
    public boolean login(String username)
    {
        // Check username
        if (!isUsernameUnique(username))
            return false;

        // Send notification: user logged in
        //Network.notifyUsers(message);
        return true;
    }

    public boolean changeUsername(String oldUsername, String newUsername)
    {
        // Check username
        if (!isUsernameUnique(newUsername))
            return false;

        // Send notification: user changed name
        //Network.notifyUsers(message);

        // Update db (to avoid duplicates, username is not stored for each message => stored once for every conversation the user add => update that name)
        return true;
    }

    private boolean isUsernameUnique(String username)
    {
        // Get active users
        String[] userList = Network.getActiveUsers();
        for (String user : userList)
            if (user.equals(username))
                return false;
        return true;
    }
}
