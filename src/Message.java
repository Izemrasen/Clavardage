import java.io.Serializable;
import java.util.Date;

public abstract class Message<Content> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public enum Marker {TEXT, DATA, EVENT};
    protected Marker marker;
    
    protected Content content;
    protected Date dateSent;
    protected Date dateReceived;
    protected boolean received;

    public Message(Marker marker, Content content)
    {
        this.marker = marker;
        this.content = content;
        //this.dateSent = new Date();
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(Content content)
    {
        this.content = content;
    }

    public Date getDateSent()
    {
        return dateSent;
    }
    
    public void setDateSent(Date dateSent)
    {
    	this.dateSent = dateSent;
    }
    
    public Date getDateReceived()
    {
        return dateReceived;
    }
    
    public void setDateReceived(Date dateReceived)
    {
    	this.dateReceived = dateReceived;
    }

    public boolean isReceived()
    {
        return received;
    }
    
    public void setReceived(boolean received)
    {
        this.received = received;
    }

    @Override
    public abstract String toString();
}
