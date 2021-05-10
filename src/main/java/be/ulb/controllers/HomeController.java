package be.ulb.controllers;

import be.ulb.controllers.views.HomeViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.dao.UserDao;
import be.ulb.exceptions.NavigationException;


import java.io.IOException;
import java.sql.SQLException;

public class HomeController extends BaseController implements HomeViewController.ViewListener, RegisterController.Listener{

    private HomeViewController viewController;

    @Override
    public void show() throws NavigationException {
        try {
            viewController = (HomeViewController) ViewLoader.getInstance().loadView("HomeView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewController.setListener(this);
    }

    public void displayError(String title, String message) {
        viewController.showError(title, message);
    }

    @Override
    public void login(String pseudo, String password) throws SQLException {
        UserDao.login(pseudo, password);
    }

    @Override
    public void register() {
        RegisterController registerController = new RegisterController(this);
        registerController.show();
    }

    @Override
    public void navigateBack() throws NavigationException {
        show();
    }
}
