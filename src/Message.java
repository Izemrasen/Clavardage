import java.util.ArrayList;
import java.util.Date;

public abstract class Message<Content>
{
    protected Content content;

    public enum Marker {TEXT, DATA, EVENT};
    protected Marker marker;

    protected Date timestamp;
    protected boolean received;

    public class History extends ArrayList<Message> {}

    public Message(Content content, boolean received, Marker marker)
    {
        this.content = content;
        this.marker = marker;
        this.timestamp = new Date();
        this.received = received;
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(Content content)
    {
        this.content = content;
    }

    public boolean isReceived()
    {
        return received;
    }

    public void addToHistory(History history)
    {
        history.add(this);
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

    public String historyToString(History history)
    {
        String serializedHistory;
        for (Message m : history) {
            //m.toString()
        }

        // Take markers into account
        return serializedHistory;
    }
}
