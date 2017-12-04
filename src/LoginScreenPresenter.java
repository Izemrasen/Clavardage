

public class LoginScreenPresenter {

    private final GuiLoginScreen guiLoginScreen;

    public LoginScreenPresenter(GuiLoginScreen guiLoginScreen) {
        this.guiLoginScreen = guiLoginScreen;
    }

    public void onComputeButtonClicked(String LoginAsText) {
        if (true/*Authentication.login(LoginAsText)*/) {
            guiLoginScreen.setVisible(false);
            guiLoginScreen.dispose();
            GuiChatSystem frame = new GuiChatSystem();
            frame.display();
        } else {

        }

    }
}

