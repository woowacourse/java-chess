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
        String query = "INSERT INTO user (name, win_count, lose_count, win_lose_difference) VALUES (?, ? ,?, ?)";
        String winCount = Integer.toString(user.getWinCount());
        String loseCount = Integer.toString(user.getLoseCount());
        String winLoseDifference = Integer.toString(user.getWinCount() - user.getLoseCount());
        DBConnector.executeUpdate(query,
                user.getName(), winCount, loseCount, winLoseDifference);
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
        String query = "INSERT INTO user (name, win_count, lose_count, win_lose_difference) VALUES (?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE win_count = ?, lose_count = ?, win_lose_difference = ?;";
        String winCount = Integer.toString(user.getWinCount());
        String loseCount = Integer.toString(user.getLoseCount());
        String winLoseDifference = Integer.toString(user.getWinCount() - user.getLoseCount());
        DBConnector.executeUpdate(query,
                user.getName(), winCount, loseCount, winLoseDifference,
                winCount, loseCount, winLoseDifference);
    }

    public void updateByName(String originalName, User modifiedUser) throws SQLException {
        String query = "UPDATE user SET name = ?, win_count = ?, lose_count = ?, win_lose_difference = ? " +
                "WHERE name = ?";
        String winCount = Integer.toString(modifiedUser.getWinCount());
        String loseCount = Integer.toString(modifiedUser.getLoseCount());
        String winLoseDifference = Integer.toString(modifiedUser.getWinCount() - modifiedUser.getLoseCount());
        DBConnector.executeUpdate(query,
                modifiedUser.getName(), winCount, loseCount, winLoseDifference,
                originalName);
    }

    public void deleteByName(String name) throws SQLException {
        String query = "DELETE FROM user WHERE name = ?";
        DBConnector.executeUpdate(query, name);
    }

    public List<User> findRankers() throws SQLException {
        String query = "SELECT * FROM user ORDER BY win_lose_difference DESC, win_count DESC LIMIT 10;";
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
