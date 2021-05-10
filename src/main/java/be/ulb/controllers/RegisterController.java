package be.ulb.controllers;

import be.ulb.controllers.views.RegisterViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.exceptions.NavigationException;
import be.ulb.models.Epidemiologiste;
import be.ulb.models.Pays;
import be.ulb.models.Utilisateur;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController extends BaseController implements RegisterViewController.ViewListener {

    private RegisterViewController viewController;
    private final Listener listener;

    public RegisterController(Listener listener) {
        this.listener=listener;
    }


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
    public void goBack() throws NavigationException{
        this.listener.navigateBack();
    }

    @Override
    public void registerUser(String nom, String prenom, String pseudo, String rue, String num, String codePostal, String ville, String password,String tel,String centre ,String type, Pays pays) throws SQLException, NavigationException {
        Utilisateur u = new Utilisateur("0",prenom,nom,rue,pseudo,num,codePostal,ville,pays.getIsoCode());
        if(u.isEpidemio()){
            Epidemiologiste e = (Epidemiologiste) u;
            e.setTel(tel);
            e.setCentre(centre);
            e.register();
        }else{
            u.register();
        }
        this.listener.navigateBack();
    }


    public interface Listener{
        /**
         * Navigate back
         * @throws NavigationException nav exception
         */
        void navigateBack() throws NavigationException;
    }
}
