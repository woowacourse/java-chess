package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.player.User;
import chess.util.DBConnector;

public class UserDAO {

    private DBConnector dbConnector;

    public UserDAO(final DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void addUser(User user) throws SQLException {
        dbConnector.executeUpdate("INSERT INTO user VALUES (?)", user.getName());
    }

    public Optional<User> findByUserName(String userName) throws SQLException {
        ResultSet rs = dbConnector.executeQuery("SELECT * FROM user WHERE name = ?", userName);
        if (!rs.next())
            return Optional.empty();

        return Optional.ofNullable(new User(rs.getString("name")));
    }

    public User updateUserNameByUserName(String originalName, String changedName) throws SQLException {
        dbConnector.executeUpdate("UPDATE user SET name = ? WHERE name = ?", changedName, originalName);

        return findByUserName(changedName).orElse(new User(changedName));
    }

    public boolean deleteUserByUserName(String name) throws SQLException {
        dbConnector.executeUpdate("DELETE FROM user WHERE name = ?", name);

        return !findByUserName(name).isPresent();
    }
}