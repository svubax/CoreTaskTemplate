package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connection;
    private Util(){}
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
