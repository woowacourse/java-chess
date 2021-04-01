package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.ConnectDB.CONNECTION;

public class ResultDAO {
    private static final String INSERT_QUERY = "INSERT INTO result (game_id, winner, loser) VALUES (?, ?, ?)";
    private static final String SELECT_WIN_COUNT_BY_USER_ID_QUERY = "SELECT COUNT(*) FROM result WHERE winner = ?";
    private static final String SELECT_LOSE_COUNT_BY_USER_ID_QUERY = "SELECT COUNT(*) FROM result WHERE loser = ?";

    public void saveGameResult(final String roomId, final int winnerId, final int loserId) throws SQLException {
        PreparedStatement statement = CONNECTION.prepareStatement(INSERT_QUERY);
        statement.setString(1, roomId);
        statement.setInt(2, winnerId);
        statement.setInt(3, loserId);
        statement.executeUpdate();
        statement.close();
    }

    public int winCountByUserId(final int id) throws SQLException {
        return countByUserId(id, SELECT_WIN_COUNT_BY_USER_ID_QUERY);
    }

    public int loseCountByUserId(final int id) throws SQLException {
        return countByUserId(id, SELECT_LOSE_COUNT_BY_USER_ID_QUERY);
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
