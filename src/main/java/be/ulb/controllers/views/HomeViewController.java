package be.ulb.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController extends BaseViewController implements Initializable {

    private ViewListener listener;

    @FXML
    TableView tableView;

    @FXML
    Button execBtn;

    @FXML
    TextArea queryArea;

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        execBtn.setOnMouseClicked(event->listener.execQuery(queryArea.getText()));
        execBtn.setOnKeyReleased(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listener.execQuery(queryArea.getText());
            }
        });
    }

    public interface ViewListener{
        /**
         * Execute a query
         * @param text the query
         */
        void execQuery(String text);
    }
}