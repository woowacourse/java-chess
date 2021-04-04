package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDAO {

    public void saveGameResult(final String roomId, final int winnerId, final int loserId) throws SQLException {
        String query = "INSERT INTO result (game_id, winner, loser) VALUES (?, ?, ?)";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try (connection; statement) {
            statement.setString(1, roomId);
            statement.setInt(2, winnerId);
            statement.setInt(3, loserId);
            statement.executeUpdate();
        } catch (Exception e) {

        }
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
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        int count = 0;
        try (connection; statement; resultSet) {
            if (!resultSet.next()) {
                return 0;
            }
            count = resultSet.getInt(1);
        } catch (Exception e) {

        }

        return count;
    }
}
