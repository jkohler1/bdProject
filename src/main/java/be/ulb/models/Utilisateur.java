package be.ulb.models;

import be.ulb.dao.UserDao;

import java.sql.SQLException;
import java.util.UUID;

public class Utilisateur {
    UUID uuid;
    String prenom;
    String nom;
    String pseudo;
    String password;
    String rue;
    String numero;
    String codePostal;
    String ville;
    String isoCode;
    boolean isEpidemio;

    public Utilisateur(UUID uuid, String prenom, String nom, String pseudo, String password, String rue, String numero, String codePostal, String ville, String isoCode, boolean isEpidemio) {
        this.uuid = uuid;
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.password = password;
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.isoCode = isoCode;
        this.isEpidemio = isEpidemio;
    }

    public Utilisateur(UUID uuid, String prenom, String nom, String pseudo, String rue, String numero, String codePostal, String ville, String isoCode) {
        this.uuid = uuid;
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.isoCode = isoCode;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Utilisateur register() throws SQLException {
        return UserDao.register(this);
    }

    public boolean isEpidemio() {
        return isEpidemio;
    }

    public void setEpidemio(boolean epidemio) {
        isEpidemio = epidemio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
