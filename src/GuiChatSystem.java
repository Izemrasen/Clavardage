

import java.awt.*;
import javax.swing.*;

public class GuiChatSystem extends JFrame {

    private final ChatSystemPresenter CSpresenter;

    public GuiChatSystem() {
        super("Clavardage");
        this.CSpresenter = new ChatSystemPresenter(this);
        this.setLayout(new BorderLayout());

        JPanel panelContainer = new JPanel();
        JPanel panelSession = new JPanel(new GridLayout(0, 1));
        JPanel panelUsers = new JPanel(new GridLayout(10, 0));
        JPanel panelFieldChange = new JPanel(new GridLayout(1, 0));

        this.setContentPane(panelContainer);

        panelContainer.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
        panelSession.setBorder(BorderFactory.createEmptyBorder(100,100,1,1));
        panelUsers.setBorder(BorderFactory.createEmptyBorder(200,10,200,1));

        // Change Login
        JLabel changeLoginLabel = new JLabel("Change your Username :");
        JTextField changeLoginTextField = new JTextField(15);
        changeLoginLabel.setLabelFor(changeLoginTextField);
        changeLoginTextField.setToolTipText("Insert your login");

        JButton computeButton = new JButton("OK");
        computeButton.addActionListener(e -> this.CSpresenter.onComputeButtonClicked(changeLoginTextField.getText()));

        // Panels
        panelSession.add(new JLabel("Chat Session"));

        //Users Panel : labels
        panelUsers.add(new JLabel("Users"));
        panelUsers.add(changeLoginLabel);
        panelFieldChange.add(changeLoginTextField);


        panelContainer.add(panelSession, BorderLayout.WEST);
        panelContainer.add(panelUsers, BorderLayout.EAST);



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}