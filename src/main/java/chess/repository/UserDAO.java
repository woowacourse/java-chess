package chess.repository;

import chess.domain.player.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final DBConnector DBConnector;

    public UserDAO(DBConnector DBConnector) {
        this.DBConnector = DBConnector;
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";
        DBConnector.executeUpdate(query, user.getName());
    }

    public User findUserByName(String name) throws SQLException {
        ResultSet resultSet = findByName(name);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("%s 이름을 가진 유저가 존재하지 않습니다.", name));
        }
        return new User(name);
    }

    private ResultSet findByName(String name) throws SQLException {
        String query = "SELECT * FROM user WHERE name = ?";
        return DBConnector.executeQuery(query, name);
    }

    public void upsert(User user) throws SQLException {
        ResultSet resultSet = findByName(user.getName());
        if (!resultSet.next()) {
            addUser(user);
        }
    }

    public void updateByName(String originalName, User modifiedUser) throws SQLException {
        String query = "UPDATE user SET name = ? WHERE name = ?";
        DBConnector.executeUpdate(query, modifiedUser.getName(), originalName);
    }

    public void deleteByName(String name) throws SQLException {
        String query = "DELETE FROM user WHERE name = ?";
        DBConnector.executeUpdate(query, name);
    }

    public void updateRecord(User winner, User loser) throws SQLException {
        String winnerQuery = "UPDATE user SET win_count = win_count + 1 WHERE name = ?";
        String loserQuery = "UPDATE user SET lose_count = lose_count + 1 WHERE name = ?;";
        DBConnector.executeUpdate(winnerQuery, winner.getName());
        DBConnector.executeUpdate(loserQuery, loser.getName());
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
