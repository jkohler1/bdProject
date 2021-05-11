package be.ulb.models;

import be.ulb.dao.UserDao;

import java.sql.SQLException;
import java.util.UUID;

public class Epidemiologiste extends Utilisateur {
    String tel, centre;

    public Epidemiologiste(UUID uuid, String prenom, String nom, String pseudo,String password,String rue, String numero, String codePostal, String ville, String isoCode, String tel, String centre, boolean isEpidemio) {
        super(uuid, prenom, nom,pseudo,password,rue,numero, codePostal, ville,isoCode,isEpidemio);
        this.centre=centre;
        this.tel=tel;
    }

    public Epidemiologiste(UUID uuid, String prenom, String nom, String pseudo,String rue, String numero, String codePostal, String ville, String isoCode, String tel, String centre, boolean isEpidemio) {
        super(uuid, prenom, nom,pseudo,rue,numero, codePostal, ville,isoCode);
        this.centre=centre;
        this.tel=tel;
        this.isEpidemio=isEpidemio;
    }

    public Epidemiologiste register() throws SQLException {
        return UserDao.register(this);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }
}
