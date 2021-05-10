package be.ulb.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Database {

    private static Connection connection = null;

    public static void initConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("java.sql.DriverManager");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","bd_user","bd_password");
        connection.setAutoCommit(false);
        connection.setSchema("db_bd");
        if(!checkIfSchemaExist()){
            //TODO : exec python script here
        }
    }

    private static boolean checkIfSchemaExist() throws SQLException {
        PreparedStatement ps = getPrepapredStatement("select * from information_schema.tables where table_schema = 'db_bd'");
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return false;
        //return rs.getInt("schema_is_present") > 0; work pas chez moi
        return true;
    }

    public static PreparedStatement getPrepapredStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static void commitStatement() throws SQLException {
        connection.commit();
    }

    public static void rollbackStatement() throws SQLException {
        connection.rollback();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
