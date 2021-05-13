package be.ulb.controllers.views;

import be.ulb.controllers.StatController;
import be.ulb.exceptions.NavigationException;
import be.ulb.models.Vaccin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StatViewController extends BaseViewController implements Initializable {
    private StatViewController.ViewListener listener;

    @FXML
    private BarChart<String, Integer> barCharNbVaccin;

    @FXML
    private TableView<Vaccin> tableNbVaccin;

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
        listener.getInfo();

    }

    public void setListener(StatViewController.ViewListener listener) {
        this.listener=listener;
    }

    public void feedTableVaccin(List<Vaccin> vaccinList){
        tableNbVaccin.getItems().addAll(vaccinList);
    }

    public interface ViewListener{
        void goBack() throws NavigationException;
        void getInfo();
    }
}
