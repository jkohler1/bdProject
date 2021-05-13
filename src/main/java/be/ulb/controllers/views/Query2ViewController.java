package be.ulb.controllers.views;

import be.ulb.models.Query;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.math.BigInteger;
import java.util.List;

public class Query2ViewController extends StatViewController {

    @FXML
    private VBox vbox;

    @Override
    public void initStats(Query query) {
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Pays");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre personne de vacciné");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        for (List<StringProperty> row : query.getRows()) {
            if(Integer.parseInt(row.get(1).getValue()) != 0) series.getData().add(new XYChart.Data(row.get(2).getValue(), Integer.parseInt(row.get(1).getValue())));
        }
        barChart.getData().add(series);
        barChart.setPrefSize(1250,590);
        vbox.getChildren().add(barChart);
    }
}
