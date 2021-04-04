package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.ConnectDB.CONNECTION;

public class ResultDAO {

    public void saveGameResult(final String roomId, final int winnerId, final int loserId) throws SQLException {
        String query = "INSERT INTO result (game_id, winner, loser) VALUES (?, ?, ?)";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, roomId);
        statement.setInt(2, winnerId);
        statement.setInt(3, loserId);
        statement.executeUpdate();
        statement.close();
    }

    public int winCountByUserId(final int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM result WHERE winner = ?";
        return countByUserId(id, query);
    }

    public int loseCountByUserId(final int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM result WHERE loser = ?";
        return countByUserId(id, query);
    }

    private int countByUserId(final int id, final String query) throws SQLException {
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return 0;
        }

        int count = resultSet.getInt(1);
        statement.close();
        return count;
    }
}
