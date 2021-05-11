package be.ulb.models;

import java.util.UUID;

public class Epidemiologiste extends Utilisateur {
    String tel, centre;

    public Epidemiologiste(UUID uuid, String prenom, String nom, String rue, String pseudo , String numero, String codePostal, String ville, String isoCode, String tel, String centre) {
        super(uuid, prenom, nom, rue, numero,pseudo, codePostal, ville, isoCode);
        this.centre=centre;
        this.tel=tel;
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
