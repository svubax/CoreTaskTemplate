package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util util;
    private final Connection connection;
    private Util() throws SQLException {
        connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test", "root","test");
    }
    public Connection getConnection() {
        return connection;
    }
    public static Util getUtil() throws SQLException {
        if (util == null) {
            util = new Util();
        }
        return util;
    }
}
