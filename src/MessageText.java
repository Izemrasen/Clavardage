public class MessageText extends Message<String>
{
    public MessageText(String content, boolean received)
    {
        super(content, received, Marker.TEXT);
    }

    public String toString()
    {
        return null;
    }
}
