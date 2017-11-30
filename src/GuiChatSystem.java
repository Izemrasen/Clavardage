
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;



import java.awt.*;
import javax.swing.*;

public class GuiChatSystem extends JFrame {

    private final ChatSystemPresenter CSpresenter;

    public GuiChatSystem() {
        super("Clavardage");
        this.CSpresenter = new ChatSystemPresenter(this);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}