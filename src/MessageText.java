import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageText extends Message<String>
{
	private static final long serialVersionUID = 1L;

	public MessageText(String content)
    {
        super(Marker.TEXT, content);
    }

    @Override
    public String toString()
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        Date msgDate = this.received ? this.getDateReceived() : this.getDateSent();
        return dt.format(msgDate) + "\t" + this.content;
    }
}
