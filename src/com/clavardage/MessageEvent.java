package com.clavardage;

import java.util.ArrayList;

public class MessageEvent extends Message<String>
{
	private static final long serialVersionUID = 1L;
	public static enum Event {ALIVE, USERNAME_CHANGED};
	public static final ArrayList<Event> events = new ArrayList<Event>()
	{
		private static final long serialVersionUID = 1L;
		{
			add(Event.ALIVE);
			add(Event.USERNAME_CHANGED);
		}
	};
	private Event event;

	private String senderName;

	public MessageEvent(Event event, String content)
	{
		super(Type.EVENT, content);
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
		return ((char) events.indexOf(this.event) + this.senderName +
			(this.content.isEmpty() ? "" : (char) 0 + this.content)).getBytes();
	}
}
