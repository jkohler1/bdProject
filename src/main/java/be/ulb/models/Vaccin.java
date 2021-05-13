package be.ulb.models;

import java.util.ArrayList;
import java.util.List;

public class Vaccin {
    private String nom;
    private String listPays[];

    public Vaccin(String nom, String listPays[]) {
        this.nom = nom;
        this.listPays = listPays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String[] getListPays() {
        return listPays;
    }

}
