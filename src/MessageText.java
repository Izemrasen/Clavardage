import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageText extends Message<String>
{
    public MessageText(String content)
    {
        super(Marker.TEXT, content);
    }

    public String toString()
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        Date msgDate = this.received ? this.getDateReceived() : this.getDateSent();
        if (msgDate == null)
        	System.out.println("<<<<<<BUG: " + this.content);
        return dt.format(msgDate) + "\t" + this.content;
    }
}
