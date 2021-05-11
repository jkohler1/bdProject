package be.ulb.dao;

import be.ulb.models.Database;
import be.ulb.models.Pays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaysDao {
    public static List<Pays> getAllCountry() throws SQLException {
        String query = "SELECT * FROM Pays";
        List<Pays>countryList=new ArrayList<>();
        PreparedStatement ps = Database.getPreparedStatement(query);
        ResultSet queryResult = ps.executeQuery();
        while (queryResult.next()){
            Pays p =new Pays(queryResult.getString("iso_code"),queryResult.getString("nom"),queryResult.getDouble("hdi"),queryResult.getInt("population"),queryResult.getDouble("superficie"));
            countryList.add(p);
        }
        return countryList;
    }
}
