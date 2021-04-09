package chess.repository;

import static chess.util.Database.closeConnection;
import static chess.util.Database.getConnection;

import chess.domain.web.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserDao {
    public Optional<User> findUserById(int id) {
        String query = "SELECT id, name FROM user WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            User user = new User(rs.getInt("id"), rs.getString("name"));
            closeConnection(connection);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Optional<User> findByName(String name) {
        String query = "SELECT id, name FROM user WHERE name = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            User user = new User(rs.getInt("id"), rs.getString("name"));
            closeConnection(connection);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public int addUser(User user) {
        String query = "INSERT INTO user(name) VALUES (?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            closeConnection(connection);
            return id;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}