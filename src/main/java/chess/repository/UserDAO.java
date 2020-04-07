package chess.repository;

import chess.domain.player.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private final DBConnector DBConnector;

    public UserDAO(DBConnector DBConnector) {
        this.DBConnector = DBConnector;
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (name, win_count, lose_count) VALUES (?, ? ,?)";
        DBConnector.executeUpdate(query,
                user.getName(), Integer.toString(user.getWinCount()), Integer.toString(user.getLoseCount()));
    }

    public Optional<User> findUserByName(String name) throws SQLException {
        ResultSet resultSet = findByName(name);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        int winCount = resultSet.getInt("win_count");
        int loseCount = resultSet.getInt("lose_count");
        return Optional.of(new User(name, winCount, loseCount));
    }

    private ResultSet findByName(String name) throws SQLException {
        String query = "SELECT * FROM user WHERE name = ?";
        return DBConnector.executeQuery(query, name);
    }

    public void upsert(User user) throws SQLException {
        ResultSet resultSet = findByName(user.getName());
        if (!resultSet.next()) {
            addUser(user);
            return;
        }
        updateByName(user.getName(), user);
    }

    public void updateByName(String originalName, User modifiedUser) throws SQLException {
        String query = "UPDATE user SET name = ?, win_count = ?, lose_count = ? WHERE name = ?";
        DBConnector.executeUpdate(query,
                modifiedUser.getName(), Integer.toString(modifiedUser.getWinCount()), Integer.toString(modifiedUser.getLoseCount()),
                originalName);
    }

    public void deleteByName(String name) throws SQLException {
        String query = "DELETE FROM user WHERE name = ?";
        DBConnector.executeUpdate(query, name);
    }

    public List<User> findRankers() throws SQLException {
        String query = "SELECT * FROM user ORDER BY win_count - lose_count DESC, win_count DESC LIMIT 10;";
        ResultSet resultSet = DBConnector.executeQuery(query);

        List<User> rankers = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int winCount = resultSet.getInt("win_count");
            int loseCount = resultSet.getInt("lose_count");
            rankers.add(new User(name, winCount, loseCount));
        }

        return rankers;
    }
}
