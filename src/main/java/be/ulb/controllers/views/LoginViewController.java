package be.ulb.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController extends BaseViewController implements Initializable {

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
        loginButton.setOnMouseClicked(event-> {
            try {
                listener.login(loginInput.getText(),passwordInput.getText());
            } catch (SQLException e) {
                showError("erreur login",e.getMessage(),false);
            }
        });
        registerButton.setOnMouseClicked(event->listener.register());
    }

    public interface ViewListener{
        void login(String pseudo, String password) throws SQLException;
        void register();
    }
}
