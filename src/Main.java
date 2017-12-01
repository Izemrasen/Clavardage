import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        User u = new User("test", "127.0.0.1", 6666);
        Session s = new Session(u);
        /*GuiLoginScreen frame = new GuiLoginScreen();
        frame.display();*/

        Network.startListening();
    }

    public static void quit()
    {
        Network.broadcast(new MessageEvent(MessageEvent.EventMarker.LOGOUT, ""));
        System.out.println("Exiting...");
        System.exit(0);
    }
}