package be.ulb;

import be.ulb.controllers.HomeController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewLoader.getInstance().setStage(primaryStage);
        ViewLoader.getInstance().initializeWindow();
        Database.initConnection();
        (new HomeController()).show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
