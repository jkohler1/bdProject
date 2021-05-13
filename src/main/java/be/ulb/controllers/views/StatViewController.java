package be.ulb.controllers.views;

import be.ulb.exceptions.NavigationException;
import be.ulb.models.Query;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class StatViewController extends BaseViewController implements Initializable {
    protected ViewListener listener;

    @FXML
    private Button goBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goBackButton.setOnMouseClicked(event-> {
            try {
                listener.goBack();
            } catch (NavigationException e) {
                showError("Nav error","erreur  lors de la navigation");
            }
        });
    }

    public void setListener(ViewListener listener){
        this.listener = listener;
    };

    public abstract void initStats(Query query);

    public interface ViewListener{
        void goBack() throws NavigationException;
    }
}
