package chess.repository;

import chess.domain.user.User;
import chess.infra.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements UserRepository {

    @Override
    public long save(final User user) {
        final String query = "INSERT INTO user (name) VALUES (?)";
        try (final Connection connection = JdbcConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(
                     query, Statement.RETURN_GENERATED_KEYS
             )
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            return getGeneratedId(preparedStatement);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private long getGeneratedId(final PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Optional<User> findByName(final String name) {
        final String query = "SELECT user_id, name FROM user WHERE name = ?";
        try (final Connection connection = JdbcConnection.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                query)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createUser(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<User> findAll() {
        final String query = "SELECT user_id, name FROM User";
        try (final Connection connection = JdbcConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            return users;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private User createUser(final ResultSet resultSet) throws SQLException {
        return new User((long) resultSet.getInt("user_id"), resultSet.getString("name"));
    }

    @Override
    public void deleteAll() {
    }
}
