package be.ulb.controllers;

import be.ulb.controllers.views.HomeViewController;
import be.ulb.controllers.views.ViewLoader;

import java.io.IOException;

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
}
