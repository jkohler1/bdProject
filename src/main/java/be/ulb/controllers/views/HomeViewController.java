package be.ulb.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController extends BaseViewController implements Initializable {

    private ViewListener listener;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField loginInput;

    @FXML
    private TextField passwordInput;

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnMouseClicked(event->listener.login(loginInput.getText(),passwordInput.getText()));
        registerButton.setOnMouseClicked(event->listener.register());
    }

    public interface ViewListener{
        void login(String pseudo, String password);
        void register();
    }
}
