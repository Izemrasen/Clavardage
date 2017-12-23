package com.clavardage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Message<Content> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static enum Type {
		DATA, EVENT, TEXT
	};

	protected Type type;
	public static final ArrayList<Type> types = new ArrayList<Type>()
	{
		private static final long serialVersionUID = 1L;
		{
			add(Type.DATA);
			// add(Type.EVENT); Useless (not stored in history)
			add(Type.TEXT);
		}
	};

	public enum Direction {
		RECEIVED, SENT
	};

	protected Direction direction;
	public static final ArrayList<Direction> directions = new ArrayList<Direction>()
	{
		private static final long serialVersionUID = 1L;
		{
			add(Direction.RECEIVED);
			add(Direction.SENT);
		}
	};

	protected Content content;
	protected Date timestamp;

	public Message(Type type, Content content)
	{
		this.type = type;
		this.content = content;
	}

	public Content getContent()
	{
		return content;
	}

	public Type getType()
	{
		return type;
	}

	// Set direction and timestamp
	public void label(Direction direction)
	{
		this.direction = direction;
		this.timestamp = new Date();
	}

	public void label(Direction direction, String senderName)
	{
		this.label(direction);
	}

	public Direction getDirection()
	{
		return direction;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	@Override
	public abstract String toString();

	// Used to send UDP datagrams
	public abstract byte[] toDatagram();
}
