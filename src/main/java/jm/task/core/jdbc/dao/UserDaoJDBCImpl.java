package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String addUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    public UserDaoJDBCImpl() {}
    public void createUsersTable() throws SQLException {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                            "(id BIGINT NOT NULL AUTO_INCREMENT , " +
                            "name CHAR(50) NOT NULL, " +
                            "lastName CHAR (100) NOT NULL, " +
                            "age TINYINT NOT NULL, " +
                            "PRIMARY KEY (id))");
        }
    }
    public void dropUsersTable() throws SQLException {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        }
    }
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement statement = Util.getConnection().prepareStatement(addUser)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        }
    }
    public void removeUserById(long id) throws SQLException {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id=" + id);
        }
    }
    public List<User> getAllUsers() throws SQLException {
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
            return users;
        }
    }
    public void cleanUsersTable() throws SQLException {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        }
    }
}
