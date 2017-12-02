import java.text.SimpleDateFormat;

public class MessageEvent extends Message<String>
{
	private static final long serialVersionUID = 1L;

	public enum Event {LOGIN, LOGOUT, USERNAME_CHANGED};
    private Event event;

    public MessageEvent(Event event, String content)
    {
        super(Marker.EVENT, event.name() + content);
    }

    @Override
    public String toString()
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        // TODO: cf. MessageText
        return dt.format(this.getDateSent()) + "\t" + this.event.name() + " (" + this.content + ")";
    }
}
