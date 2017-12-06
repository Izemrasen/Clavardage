package com.clavardage;

public class MessageEvent extends Message<String>
{
	private static final long serialVersionUID = 1L;

	public enum Event {LOGIN, LOGOUT, USERNAME_CHANGED};
    private Event event;

    public MessageEvent(Event event, String content)
    {
        super(Marker.EVENT, event.name() + content);
    	this.event = event;
    }

    @Override
    public String toString()
    {
        return this.event.name() + " (" + this.content + ")";
    }
}
