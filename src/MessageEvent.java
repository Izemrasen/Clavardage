import java.text.SimpleDateFormat;

public class MessageEvent extends Message<String>
{
    public enum EventMarker {LOGIN, LOGOUT, USERNAME_CHANGED};
    private EventMarker eventMarker;

    public MessageEvent(EventMarker eventMarker, String content)
    {
        super(eventMarker.name() + content, Marker.EVENT);
    }

    @Override
    public String toString()
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
        return dt.format(this.getTimestamp()) + "\t" + this.eventMarker.name() + " (" + this.content + ")";
    }
}
