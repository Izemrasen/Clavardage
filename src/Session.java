import java.util.ArrayList;

public class Session
{
    private Message.History history;
    private User remoteUser;
    // Way to id connection (remote IP addr, local/remote port, etc.)

    public Session(User remoteUser)
    {
        this.remoteUser = remoteUser;
    }

    public Message.History getHistory()
    {
        return history;
    }

    public User getRemoteUser()
    {
        return remoteUser;
    }
}
