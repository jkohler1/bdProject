package be.ulb.controllers.views;

import be.ulb.models.Query;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Query1ViewController extends StatViewController {

    @FXML
    private VBox vbox;

    @Override
    public void initStats(Query query) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Pays");

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Date");

        ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Plus de 5000 cas");

        for (List<StringProperty> row : query.getRows()) {
            dataSeries1.getData().add(new XYChart.Data(row.get(1).getValue(), row.get(0).getValue()));
        }
        scatterChart.getData().add(dataSeries1);
        scatterChart.setPrefSize(1234,584);
        vbox.getChildren().add(scatterChart);
    }
}
