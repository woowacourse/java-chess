package chess.dao;

import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResultDAO {

    public void saveGameResult(final String roomId, final int winnerId, final int loserId) {
        String query = "INSERT INTO result (game_id, winner, loser) VALUES (?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, roomId);
            statement.setInt(2, winnerId);
            statement.setInt(3, loserId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("게임 결과 저장을 실패했습니다.");
        }
    }

    public int winCountByUserId(final int id) {
        String query = "SELECT COUNT(*) FROM result WHERE winner = ?";
        return countByUserId(id, query);
    }

    public int loseCountByUserId(final int id) {
        String query = "SELECT COUNT(*) FROM result WHERE loser = ?";
        return countByUserId(id, query);
    }

    private int countByUserId(final int id, final String query) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = countStatement(query, connection, id);
             ResultSet resultSet = statement.executeQuery();) {
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DataAccessException("유저별 승 패 정보 load가 실패했습니다.");
        }
    }

    private PreparedStatement countStatement(final String query, final Connection connection, final int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        return statement;
    }
}
