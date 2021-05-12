package be.ulb;

import be.ulb.controllers.LoginController;
import be.ulb.controllers.HomeController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewLoader.getInstance().setStage(primaryStage);
        ViewLoader.getInstance().initializeWindow();
        Database.initConnection();
        (new LoginController()).show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
