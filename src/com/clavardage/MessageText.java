package com.clavardage;

public class MessageText extends Message<String>
{
	private static final long serialVersionUID = 1L;

	public MessageText(String content)
	{
		super(Type.TEXT, content);
	}

	@Override
	public String toString()
	{
		return this.content;
	}

	@Override
	public byte[] toDatagram()
	{
		// Forbid broadcast for MessageText by security
		return null;
	}
}
