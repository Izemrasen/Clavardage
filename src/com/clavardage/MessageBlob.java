package com.clavardage;

import java.util.Date;

public class MessageBlob extends Message<byte[]>
{
	private static final long serialVersionUID = 1L;

	public MessageBlob(Type type, Direction direction, Date timestamp, byte[] content)
	{
		super(type, content);
		this.direction = direction;
		this.timestamp = timestamp;
	}

	@Override
	public String toString()
	{
		return new String(this.content);
	}

	@Override
	public byte[] toDatagram()
	{
		// TODO Not destined to be sent
		return null;
	}

}
