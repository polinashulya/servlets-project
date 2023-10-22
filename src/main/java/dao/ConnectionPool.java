package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String username = "postgres";
    private static final String password = "1234";
    private static final String url = "jdbc:postgresql://localhost:5432/servlets-project";
    public void init(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (Exception e){

        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

}
