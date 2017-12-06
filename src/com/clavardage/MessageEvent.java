package com.clavardage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageEvent extends Message<String>
{
	private static final long serialVersionUID = 1L;

	public enum Event {LOGIN, LOGOUT, USERNAME_CHANGED};
    private Event event;

    public MessageEvent(Event event, String content)
    {
        super(Marker.EVENT, event.name() + content);
    }

    @Override
    public String toString()
    {
    	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");
        Date msgDate = this.getDirection() == Message.Direction.SENT ? this.getDateSent() : this.getDateReceived();
        return dt.format(msgDate) + "\t" + this.event.name() + " (" + this.content + ")";
    }
}
