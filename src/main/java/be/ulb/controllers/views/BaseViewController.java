package be.ulb.controllers.views;

import javafx.scene.control.Alert;

public class BaseViewController {

    protected BaseViewController(){}

    public void showInformation(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.show();
    }
}
