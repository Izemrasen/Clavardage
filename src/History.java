import java.util.ArrayList;

public class History
{
    private ArrayList<Message> messages;
    private User remoteUser; // TODO: setter (or put it elsewhere)

    public History(User remoteUser)
    {
        this.messages = new ArrayList<Message>();
        this.remoteUser = remoteUser;
    }

    public ArrayList<Message> getMessages()
    {
        return messages;
    }

    public void addMessage(Message m)
    {
    	this.messages.add(m);
    }
    
    public User getRemoteUser()
    {
        return remoteUser;
    }

    public static void loadHistory(Session session)
    {
        // Open file or database
        // Read
        // Convert to History
        //session.history = xxx
    }

    public static void saveHistory()
    {
        // Contrary of loadHistory() (write file or db, serialize messages)
    }
    
    @Override
    public String toString()
    {
        String serializedHistory = "";
        boolean first = true;
        for (Message m : messages) {
            if (!first)
                serializedHistory += "\n";
            else
                first = false;
            serializedHistory += "<";
            serializedHistory += m.isReceived() ? this.remoteUser.getUsername() : Main.getUsername();
            serializedHistory += ">\t" + m.toString();
        }
        return serializedHistory;
    }
}
