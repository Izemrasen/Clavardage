

import java.awt.*;
import javax.swing.*;

public class GuiChatSystem extends JFrame {

    private final ChatSystemPresenter CSpresenter;

    private User GuiUser;

    public GuiChatSystem() {
        super("Clavardage");
        this.CSpresenter = new ChatSystemPresenter(this);
        this.setLayout(new BorderLayout());

        // Change Login
        JLabel changeLoginLabel = new JLabel("Change your Username :");
        JTextField changeLoginTextField = new JTextField(15);
        changeLoginLabel.setLabelFor(changeLoginTextField);
        changeLoginTextField.setToolTipText("Insert your login");

        // Button OK
        JButton computeButton = new JButton("Confirm");
        computeButton.setSize(10,10);
        //computeButton.addActionListener(e -> this.CSpresenter.onComputeButtonClicked(changeLoginTextField.getText()));


        /******** RIGHT PANEL ********/
        JPanel rightPanel = new JPanel(new GridLayout(2, 0));

        // User List Panel
        JScrollPane userListPanel = new JScrollPane();

        // Settings Panel
        JPanel settingsPanel = new JPanel(new GridLayout(4,0));
        settingsPanel.add(changeLoginLabel);
        settingsPanel.add(changeLoginTextField);
        settingsPanel.add(computeButton);


        for(int i=0; i < GuiUser.getActiveUsers().size() ; i++){
            JButton connectButton = new JButton(GuiUser.getActiveUsers().get(i).getUsername());
            connectButton.setSize(10,10);
            userListPanel.add(connectButton);
            //connectButton.addActionListener(e -> this.CSpresenter.onConnectButtonClicked());
        }

        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Users"));
        rightPanel.add(userListPanel);
        rightPanel.add(settingsPanel);



        /******** LEFT PANEL ********/
        JScrollPane leftPanel= new JScrollPane();
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Chat Session"));

        /******** MAIN PANEL ********/
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        this.setContentPane(panelContainer);
        panelContainer.add(leftPanel, BorderLayout.CENTER);
        panelContainer.add(rightPanel, BorderLayout.EAST);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        this.pack();
        this.setSize(800,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}