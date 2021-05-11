package be.ulb.controllers.views;

import be.ulb.exceptions.NavigationException;
import be.ulb.models.Epidemiologiste;
import be.ulb.models.Pays;
import be.ulb.models.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterViewController extends BaseViewController implements Initializable {

    private RegisterViewController.ViewListener listener;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField inputNom;

    @FXML
    private TextField inputPseudo;

    @FXML
    private TextField inputPrenom;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputRue;

    @FXML
    private TextField inputNum;

    @FXML
    private TextField inputCodePostal;

    @FXML
    private TextField inputVille;

    @FXML
    private TextField inputCentre;

    @FXML
    private TextField inputTel;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ChoiceBox<String> choiceBoxCountry;

    @FXML
    private Button registerButton;


    public void setListener(RegisterViewController.ViewListener listener){
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add("Utilisateur");
        choiceBox.getItems().add("Epidémiologiste");
        choiceBoxCountry.getItems().add("Bel");
        choiceBox.setOnAction(event->{
            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            if(selectedItem.equals("Utilisateur")){
                this.inputCentre.setVisible(false);
                this.inputTel.setVisible(false);
            }else{
                this.inputCentre.setVisible(true);
                this.inputTel.setVisible(true);
            }
        });
        inputTel.setVisible(false);
        inputCentre.setVisible(false);
        this.goBackButton.setOnMouseClicked(event->{
            try {
                listener.goBack();
            } catch (NavigationException e) {
                showError("Problème de navigation",e.getMessage(),false);
            }
        });
        registerButton.setOnMouseClicked(event->{
            try {
                listener.registerUser(inputNom.getText(), inputPrenom.getText(),
                        inputPseudo.getText(), inputRue.getText(), inputNum.getText(),
                        inputCodePostal.getText(), inputVille.getText(),
                        inputPassword.getText(),inputCentre.getText(),inputTel.getText(),choiceBox.getSelectionModel().getSelectedItem(), choiceBoxCountry.getSelectionModel().getSelectedItem());
            } catch (SQLException | NavigationException e) {
                showError("problème d'insert",e.getMessage(),false);
            }
        });
    }

    public interface ViewListener{
        void goBack() throws NavigationException;
        void registerUser(String nom, String prenom, String pseudo, String rue, String num, String codePostal, String ville, String password,String tel,String centre, String type, String isoCode) throws SQLException, NavigationException;

    }
}
