package be.ulb.controllers.views;

import be.ulb.models.Query;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query5ViewController extends StatViewController{

    @FXML
    private VBox vbox;


    @Override
    public void initStats(Query query) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre d'hospitalisations");

        LineChart lineChart = new LineChart(xAxis,yAxis);

        Map<String, XYChart.Series> dataSeriesList = new HashMap<>();

        for (List<StringProperty> row : query.getRows()) {
            String pays = row.get(0).getValue();
            if(!dataSeriesList.containsKey(pays)){
                XYChart.Series series = new XYChart.Series();
                series.setName(pays);
                dataSeriesList.put(pays, series);
            }
            dataSeriesList.get(pays).getData().add(new XYChart.Data(row.get(3).getValue(), Integer.parseInt(row.get(2).getValue())));
        }
        dataSeriesList.values().forEach(series -> {
            lineChart.getData().add(series);
        });

        lineChart.setPrefSize(1260,560);
        vbox.getChildren().add(lineChart);
    }

}
