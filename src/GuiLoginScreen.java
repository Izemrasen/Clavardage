
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


import java.awt.*;
import javax.swing.*;

public class GuiLoginScreen extends JFrame {

    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 150;
    private final LoginScreenPresenter LSpresenter;

    public GuiLoginScreen() {
        super("Clavardage");
        this.LSpresenter = new LoginScreenPresenter(this);


        JLabel loginLabel = new JLabel("Login");
        JTextField loginTextField = new JTextField(15);
        loginLabel.setLabelFor(loginTextField);
        loginTextField.setToolTipText("Insert your login");

        JButton computeButton = new JButton("Connect");
        //computeButton.addActionListener(e -> this.LSpresenter.onComputeButtonClicked(loginTextField.getText()));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(loginLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(loginTextField);

        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 50, 20));
        add(labelPane, WEST);
        add(fieldPane, EAST);
        add(computeButton, EAST);

        loginTextField.requestFocus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}