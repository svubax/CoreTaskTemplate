package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password ="test";
    private static Connection connection;
    private Util(){}
    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection( url, user,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
