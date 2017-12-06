package com.clavardage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History
{
	private ArrayList<Message<?>> messages;
    private User remoteUser; // TODO: put it elsewhere (session) or link History w/ Session

    public History(User remoteUser)
    {
        this.messages = new ArrayList<Message<?>>();
        this.remoteUser = remoteUser;
    }

    public ArrayList<Message<?>> getMessages()
    {
        return messages;
    }

    public void add(Message<?> m)
    {
        this.messages.add(m);
    }
    
    public User getRemoteUser()
    {
        return remoteUser;
    }
    
    // TODO: find a way to get owning class (i.e. user1.history.load() -> I want to get user1 from history)
    // Easy way: field 'remoteUser'
    public static void load(User remoteUser)
    {
        // Open file or database
        // Read
        // Convert to History
        //user.history = xxx
    }

    public static void save()
    {
        // Contrary of loadHistory() (write file or db, serialize messages)
    }
    
    @Override
    public String toString()
    {
        String serializedHistory = "";
        boolean first = true;
        for (Message<?> m : messages) {
            if (!first)
                serializedHistory += "\n";
            else
                first = false;
            serializedHistory += "<";
            serializedHistory += m.getDirection() == Message.Direction.SENT ? this.remoteUser.getUsername() : Main.getUsername();
            serializedHistory += ">\t";
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");
            Date msgDate = m.getDirection() == Message.Direction.SENT ? m.getDateSent() : m.getDateReceived();
            serializedHistory += dt.format(msgDate) + "\t" + m.toString();
        }
        return serializedHistory;
    }
}
