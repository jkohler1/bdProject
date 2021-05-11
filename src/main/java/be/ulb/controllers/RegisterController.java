package be.ulb.controllers;

import be.ulb.controllers.views.RegisterViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.exceptions.NavigationException;
import be.ulb.models.Epidemiologiste;
import be.ulb.models.Pays;
import be.ulb.models.Utilisateur;
import be.ulb.utils.AllCountrySingleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
            viewController.setListener(this);
            viewController.setCountry(AllCountrySingleton.getAllCountry());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void goBack() throws NavigationException{
        this.listener.navigateBack();
    }

    @Override
    public void registerUser(String nom, String prenom, String pseudo, String rue, String num, String codePostal, String ville, String password,String tel,String centre ,String type, String isoCode) throws SQLException, NavigationException {
        if(type.equals("Utilisateur")){
            Utilisateur u = new Utilisateur(UUID.randomUUID(),prenom,nom,pseudo,password,rue,num,codePostal,ville,isoCode,false);
            u.register();
        }else{
            Epidemiologiste e = new Epidemiologiste(UUID.randomUUID(),prenom,nom,pseudo,password,rue,num,codePostal,ville,isoCode,tel,centre,true);
            e.setTel(tel);
            e.setCentre(centre);
            e.register();

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
