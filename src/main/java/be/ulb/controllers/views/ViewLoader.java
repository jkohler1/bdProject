package be.ulb.controllers.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {

    private Stage stage;
    private static ViewLoader viewLoader = null;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    public Stage getStage(){
        return stage;
    }

    public static ViewLoader getInstance(){
        if(viewLoader == null){
            viewLoader = new ViewLoader();
        }
        return viewLoader;
    }

    private ViewLoader(){};

    public void initializeWindow() {
        stage.setTitle("INFO-H303 | Projet de base de donnée");
        stage.centerOnScreen();
    }

    public BaseViewController loadView(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        loader.load();
        BaseViewController viewController = loader.getController();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
        return viewController;
    }
}
