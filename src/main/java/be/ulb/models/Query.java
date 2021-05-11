package be.ulb.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query {

    private final String query;
    private final List<String> fields;
    private final List<List<StringProperty>> rows;

    public Query(String query){
        this.query = query;
        fields = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public void exec() throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(query);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
            fields.add(resultSetMetaData.getColumnName(i));
        }
        while(rs.next()){
            List<StringProperty> row = new ArrayList<>();
            for(String field : fields){
                row.add(new SimpleStringProperty(String.valueOf(rs.getObject(field))));
            }
            rows.add(row);
        }
    }

    public List<String> getFields() {
        return fields;
    }

    public List<List<StringProperty>> getRows() {
        return rows;
    }
}
