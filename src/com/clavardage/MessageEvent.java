package com.clavardage;

import java.util.ArrayList;

public class MessageEvent extends Message<String>
{
	private static final long serialVersionUID = 1L;
	public static enum Event {ANNOUNCEMENT, USERNAME_CHANGED};
    private static final ArrayList<Event> events = new ArrayList<Event>()
    {
		private static final long serialVersionUID = 1L;
		{
			add(Event.ANNOUNCEMENT);
			add(Event.USERNAME_CHANGED);
		}
	};
    private Event event;
    
    private String senderName;

    public MessageEvent(Event event, String content)
    {
        super(Marker.EVENT, content);
    	this.event = event;
    }
    
    @Override
    public void label(Direction direction, String senderName)
    {
    	this.senderName = senderName;
    	this.label(direction);
    }

    @Override
    public String toString()
    {
        return "<" + this.senderName + "> " + this.event.name() + " (" + this.content + ")";
    }

	@Override
	public byte[] toDatagram()
	{
		return (this.senderName + (char) 0 + events.indexOf(this.event) + (char) 0 + this.content).getBytes();
	}
}
