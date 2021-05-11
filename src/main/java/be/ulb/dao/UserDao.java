package be.ulb.dao;

import be.ulb.models.Database;
import be.ulb.models.Epidemiologiste;
import be.ulb.models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import be.ulb.utils.BcryptPasswordEncoderSingleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao {
    public static Utilisateur login(String pseudo, String password) throws SQLException {
        String query = "SELECT * FROM Utilisateurs WHERE nom_utilisateur=?;";
        PreparedStatement ps = Database.getPreparedStatement(query);
        ps.setString(1, pseudo);
        ResultSet queryResult = ps.executeQuery();
        if (queryResult.next()) {
            String hashPassword = queryResult.getString("mdp");
            BCryptPasswordEncoder encoder = BcryptPasswordEncoderSingleton.getEncoder();
            if (!encoder.matches(password, hashPassword)) {
                return null;
            }
            Utilisateur u = new Utilisateur(UUID.fromString(queryResult.getString("uuid")), queryResult.getString("nom"),
                    queryResult.getString("prenom"), queryResult.getString("nom_utilisateur"),
                    queryResult.getString("ad_rue"), queryResult.getString("ad_numero"),
                    queryResult.getString("ad_code_postal"), queryResult.getString("ad_ville"), queryResult.getString("iso_code"));
            query = "SELECT * FROM Epidemiologistes WHERE uuid_utilisateur=?;";
            ps = Database.getPreparedStatement(query);
            ResultSet queryResult2 = ps.executeQuery();
            if (queryResult2.next()) {
                Epidemiologiste e = (Epidemiologiste) u;
                e.setEpidemio(true);
                e.setCentre(queryResult2.getString("centre"));
                e.setTel(queryResult2.getString("tel"));
                return e;
            } else {
                u.setEpidemio(false);
                return u;
            }
        }
        return null;
    }

    public static Utilisateur register(Utilisateur u) throws SQLException {
        BCryptPasswordEncoder encoder = BcryptPasswordEncoderSingleton.getEncoder();
        String password=encoder.encode(u.getPassword());
        System.out.println(password.length());
        String query = "INSERT INTO Utilisateurs(uuid,nom,prenom,nom_utilisateur,ad_rue,ad_numero,ad_code_postal,ad_ville,password,iso_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = Database.getPreparedStatement(query, RETURN_GENERATED_KEYS);
        ps.setString(1,u.getUuid().toString());
        ps.setString(2,u.getNom());        ps.setString(3,u.getPrenom());
        ps.setString(4,u.getPseudo());
        ps.setString(5,u.getRue());
        ps.setString(6,u.getNumero());
        ps.setString(7,u.getCodePostal());
        ps.setString(8,u.getVille());
        ps.setString(9,password);
        ps.setString(10,u.getIsoCode());
        ps.executeUpdate();
        ResultSet queryResult = ps.getGeneratedKeys();
        if(!queryResult.next()){
            return null;
        }
        if(u.isEpidemio()){
            Epidemiologiste e = (Epidemiologiste) u;
            query = "INSERT INTO Epidemiologistes VALUES(?, ?, ?);";
            ps = Database.getPreparedStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1,e.getUuid().toString());
            ps.setString(2,e.getCentre());
            ps.setString(3,e.getTel());
            queryResult = ps.executeQuery();
        }
        if(!queryResult.next()) return null;
        return u;
    }

}
