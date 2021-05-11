package be.ulb.controllers.views;


import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class HomeViewController extends BaseViewController implements Initializable {

    private ViewListener listener;

    @FXML
    TableView tableView;

    @FXML
    Button execBtn;

    @FXML
    TextArea queryArea;

    @FXML
    Button statsBtn;

    @FXML
    ChoiceBox<String> queryBox;

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        execBtn.setOnMouseClicked(event->listener.execQuery(queryArea.getText()));
        statsBtn.setOnMouseClicked(event -> listener.showStats());
        queryBox.setValue("Select a pre-defined query");
        queryBox.setOnAction(event -> listener.loadQuery(queryBox.getSelectionModel().getSelectedItem()));

    }

    /**
     * After executing a query, this method is used to show results
     * @param fields all fields containing in the result
     * @param rows all results
     */
    public void setTableViewData(List<String> fields, List<List<StringProperty>> rows) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        for(int i=0;i<fields.size();i++){
            TableColumn<List<StringProperty>, String> column = new TableColumn<>(fields.get(i));
            int finalI = i;
            column.setCellValueFactory(row -> row.getValue().get(finalI));
            tableView.getColumns().add(column);
        }
        tableView.getItems().addAll(rows);
        tableView.refresh();
    }

    public void setListOfQueries(Set<String> listOfQueries) {
        for(String title : listOfQueries){
            queryBox.getItems().add(title);
        }
    }

    public void setQueryArea(String query) {
        queryArea.setText(query);
    }

    public interface ViewListener{
        /**
         * Execute a query
         * @param text the query
         */
        void execQuery(String text);

        /**
         * go to the stats view
         * the view will contains stats for current query
         */
        void showStats();

        /**
         * Load a pre define query
         * @param selectedQuery the selected query
         */
        void loadQuery(String selectedQuery);
    }
}
