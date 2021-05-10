package be.ulb.controllers.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * After executing a query, this method is used to show results
     * @param fields all fields containing in the result
     * @param rows all results
     */
    public void setTableViewData(List<String> fields, List<List<StringProperty>> rows) {
        for(int i=0;i<fields.size();i++){
            TableColumn<List<StringProperty>, String> column = new TableColumn<>(fields.get(i));
            int finalI = i;
            column.setCellValueFactory(row -> row.getValue().get(finalI));
            tableView.getColumns().add(column);
        }
        tableView.getItems().addAll(rows);
        tableView.refresh();
    }

    public interface ViewListener{
        /**
         * Execute a query
         * @param text the query
         */
        void execQuery(String text);
    }
}
