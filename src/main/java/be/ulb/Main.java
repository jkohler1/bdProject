package be.ulb;

import be.ulb.controllers.HomeController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewLoader.getInstance().setStage(primaryStage);
        ViewLoader.getInstance().initializeWindow();
        HomeController controller = new HomeController();
        controller.show();
    }


    public static void main(String[] args) {
        try {
            Database.initConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("DriverManager not found : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection to database failed : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while trying to open python script : " + e.getMessage());
        }
        launch(args);
    }
}
