package be.ulb.utils;

import be.ulb.models.Utilisateur;

public class Helper {
    private static Utilisateur u = null;

    public static Utilisateur getUtilisateur() {
        return u;
    }

    public static void setUtilisateur(Utilisateur u) {
        Helper.u = u;
    }
}
