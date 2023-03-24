package chess.domain.userAccess.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static chess.domain.userAccess.DbConnection.getConnection;

public final class UserDao {

    public void createUser(User user) {
        try {
            String query = "INSERT INTO user VALUES(?)";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.userId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] addUser");
        }
    }

    public Optional<User> findUserById(String userId) {
        try {
            String query = "SELECT * FROM user WHERE user_id = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(userId));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] findByUserId");
        }
    }
}
