package chess.repository;

import static chess.util.Database.closeConnection;
import static chess.util.Database.getConnection;

import chess.domain.web.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findByName(String name) {
        String query = "SELECT id, name FROM user WHERE name = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User user = new User(rs.getInt("id"), rs.getString("name"));
            closeConnection(connection);
            return user;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO user(name) VALUES (?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            closeConnection(connection);
        } catch (SQLException e) {
            throw new IllegalArgumentException("error-sql");
        }
    }
}