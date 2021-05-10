package be.ulb.models;

import java.io.IOException;
import java.sql.*;

public class Database {

    private static Connection connection = null;

    public static void initConnection() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        Class.forName("java.sql.DriverManager");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","bd_user","bd_password");
        connection.setAutoCommit(false);
        if(!checkIfSchemaExist()){
            ProcessBuilder processBuilder = new ProcessBuilder("python" ,"src/main/java/be/ulb/scripts/Script.py");
            processBuilder.redirectErrorStream(true);
            processBuilder.inheritIO();
            Process p = processBuilder.start();
            p.waitFor();
        }
        connectToSchema();
    }

    private static void connectToSchema() throws SQLException {
        PreparedStatement ps = getPreparedStatement("use db_bd;");
        ps.executeUpdate();
    }

    private static boolean checkIfSchemaExist() throws SQLException {
        PreparedStatement ps = getPreparedStatement("select count(*) as is_schema_present from information_schema.tables where table_schema = 'db_bd'");
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return false;
        return rs.getInt("is_schema_present") > 0;
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
