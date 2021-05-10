package be.ulb.controllers;

import be.ulb.controllers.views.RegisterViewController;
import be.ulb.controllers.views.ViewLoader;

import java.io.IOException;

public class RegisterController extends BaseController implements RegisterViewController.ViewListener {

    private RegisterViewController viewController;


    @Override
    public void show() {
        try {
            viewController = (RegisterViewController) ViewLoader.getInstance().loadView("RegisterView.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
            viewController.setListener(this);
    }

    @Override
    public void navigateBack() {

    }
}
