package be.ulb.controllers;

import be.ulb.controllers.views.HomeViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Query;


import java.io.IOException;
import java.sql.SQLException;

public class HomeController extends BaseController implements HomeViewController.ViewListener{

    private HomeViewController viewController;

    @Override
    public void show() {
        try {
            viewController = (HomeViewController) ViewLoader.getInstance().loadView("HomeView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewController.setListener(this);
    }

    @Override
    public void execQuery(String text) {
        Query query = new Query(text);
        try {
            query.exec();
        } catch (SQLException e) {
            System.out.println("Error while executing query : " + e.getMessage());
        }
    }
}
