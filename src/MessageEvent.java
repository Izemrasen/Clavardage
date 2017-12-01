public class MessageEvent extends Message<String>
{
    private enum EventMarker {LOGIN, LOGOUT, USERNAME_CHANGED};
    private EventMarker eventMarker;

    public MessageEvent(EventMarker eventMarker, String content, boolean received)
    {
        super(eventMarker.name() + content, received, Marker.EVENT);
    }

    public String toString()
    {
        return null;
    }
}
