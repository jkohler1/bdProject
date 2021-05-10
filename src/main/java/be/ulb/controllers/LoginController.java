package be.ulb.controllers;

import be.ulb.controllers.views.LoginViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.dao.UserDao;
import be.ulb.exceptions.NavigationException;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends BaseController implements LoginViewController.ViewListener, RegisterController.Listener{

    private LoginViewController viewController;

    @Override
    public void show() throws NavigationException {
        try {
            viewController = (LoginViewController) ViewLoader.getInstance().loadView("LoginView.fxml");
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
