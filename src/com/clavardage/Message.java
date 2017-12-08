package com.clavardage;
import java.io.Serializable;
import java.util.Date;

public abstract class Message<Content> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public enum Marker {TEXT, DATA, EVENT};
    protected Marker marker;

    public enum Direction {SENT, RECEIVED};
    protected Direction direction;
    
    protected Content content;
    protected Date dateSent;
    protected Date dateReceived;

    public Message(Marker marker, Content content)
    {
        this.marker = marker;
        this.content = content;
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(Content content)
    {
        this.content = content;
    }

    // Set direction and date according to it
    public void label(Direction direction)
    {
        this.direction = direction;
        switch (direction) {
            case SENT:
                this.dateSent = new Date();
                break;
            case RECEIVED:
                this.dateReceived = new Date();
                break;
        }
    }
    
    public void label(Direction direction, String senderName)
    {
    	this.label(direction);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public Date getDateSent()
    {
        return dateSent;
    }

    public Date getDateReceived()
    {
        return dateReceived;
    }

    @Override
    public abstract String toString();
    
    // Used to send UDP datagrams
    public abstract byte[] toDatagram();
}
