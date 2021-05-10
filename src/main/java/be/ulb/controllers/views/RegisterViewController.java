package be.ulb.controllers.views;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterViewController extends BaseViewController implements Initializable {

    private RegisterViewController.ViewListener listener;


    public void setListener(RegisterViewController.ViewListener listener){
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public interface ViewListener{
        void navigateBack();
    }
}
