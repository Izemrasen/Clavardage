import java.text.SimpleDateFormat;

public class MessageEvent extends Message<String>
{
    public enum EventMarker {LOGIN, LOGOUT, USERNAME_CHANGED};
    private EventMarker eventMarker;

    public MessageEvent(EventMarker eventMarker, String content)
    {
        super(Marker.EVENT, eventMarker.name() + content);
    }

    @Override
    public String toString()
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        // TODO: cf. MessageText
        return dt.format(this.getDateSent()) + "\t" + this.eventMarker.name() + " (" + this.content + ")";
    }
}
