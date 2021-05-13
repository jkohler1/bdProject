package be.ulb.controllers.views;

import be.ulb.models.Query;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import java.util.List;

public class Query3ViewController extends StatViewController{

    @FXML
    private PieChart pieChart;

    @Override
    public void initStats(Query query) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (List<StringProperty> row : query.getRows()) {
            pieChartData.add(new PieChart.Data(row.get(0).getValue() + " : " + row.get(1).toString().split(",").length + " pays",
                    row.get(1).toString().split(",").length));
        }
        pieChart.setData(pieChartData);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitle("Vaccins les plus utilisés dans le monde");
    }
}
