package be.ulb.controllers;

import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Database;

import java.io.IOException;
import java.sql.SQLException;

public class WaitingController extends BaseController{
    @Override
    public void show() throws IOException {
        ViewLoader.getInstance().loadView("WaitingView.fxml");
        Thread thread = dataThread();
        try {
            thread.join();
            (new HomeController()).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Thread dataThread(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Database.fetchData();
                } catch (SQLException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return thread;
    }
}
