

public class LoginScreenPresenter {

    private final GuiLoginScreen guiLoginScreen;

    public LoginScreenPresenter(GuiLoginScreen guiLoginScreen) {
        this.guiLoginScreen = guiLoginScreen;
    }

    public void onComputeButtonClicked(String username) {
        if (Authentication.login(username)) {
            guiLoginScreen.setVisible(false);
            guiLoginScreen.dispose();
            GuiChatSystem frame = new GuiChatSystem();
            frame.display();
        } else {
            guiLoginScreen.usernameAlreadyInUse(username);
        }

    }
}

