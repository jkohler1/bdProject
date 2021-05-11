package be.ulb.utils;

import be.ulb.dao.PaysDao;
import be.ulb.models.Pays;

import java.sql.SQLException;
import java.util.List;

public class AllCountrySingleton {
    private static List<Pays> INSTANCE =null;

    private AllCountrySingleton() throws SQLException {
        INSTANCE= PaysDao.getAllCountry();
    }
    public static List<Pays> getAllCountry() throws SQLException {
        if(INSTANCE==null){
            new AllCountrySingleton();
        }
        return INSTANCE;
    }
}
