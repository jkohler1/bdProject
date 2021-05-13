package be.ulb.controllers.views;

import be.ulb.models.Query;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.List;

public class Query4ViewController extends StatViewController{

    @FXML
    private VBox vbox;

    @Override
    public void initStats(Query query) {
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Pays");
        xAxis.getCategories().addAll("Population", "Hospitalisation");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de personne");

        StackedBarChart  stackedBarChart = new StackedBarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Population");

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Hospitalisation");

        for (List<StringProperty> row : query.getRows()) {
            dataSeries1.getData().add(new XYChart.Data(row.get(1).getValue(), Integer.parseInt(row.get(2).getValue())));
            dataSeries2.getData().add(new XYChart.Data(row.get(1).getValue(), Integer.parseInt(row.get(0).getValue())));
        }

        stackedBarChart.getData().add(dataSeries1);
        stackedBarChart.getData().add(dataSeries2);

        stackedBarChart.setPrefSize(1250,550);
        vbox.getChildren().add(stackedBarChart);
    }
}
