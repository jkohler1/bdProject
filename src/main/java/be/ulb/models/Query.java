package be.ulb.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    private final String query;

    public Query(String query){
        this.query = query;
    }

    public void exec() throws SQLException {
        PreparedStatement ps = Database.getPrepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs);
        }
    }
}
