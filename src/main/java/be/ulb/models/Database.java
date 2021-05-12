package be.ulb.models;

import java.io.IOException;
import java.sql.*;

public class Database {

    private static Connection connection = null;

    public static void initConnection() throws ClassNotFoundException, SQLException{
        Class.forName("java.sql.DriverManager");
        connection = DriverManager.getConnection("jdbc:mysql://info-h303.mysql.database.azure.com:3306","bd_user@info-h303","bd_password=303");
        connection.setAutoCommit(false);
        connectToSchema();
    }

    private static void connectToSchema() throws SQLException {
        PreparedStatement ps = getPreparedStatement("use db_bd;");
        ps.executeUpdate();
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static PreparedStatement getPreparedStatement(String sql, int s) throws SQLException {
        return connection.prepareStatement(sql,s);
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
