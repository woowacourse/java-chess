package chess.dao;

import chess.controller.web.dto.state.StateResponseDto;
import chess.domain.manager.ChessManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDao {

    public Long saveState(final ChessManager chessManager, final Long gameId) {
        final String query =
                "INSERT INTO state(gameId, turn_owner, turn_number, isPlaying) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, chessManager.turnOwner().name());
            pstmt.setInt(3, chessManager.turnNumber());
            pstmt.setBoolean(4, chessManager.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StateResponseDto findStateByGameId(final Long gameId) {
        final String query =
                "SELECT * from state where gameId = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new StateResponseDto(
                        resultSet.getString("turn_owner"),
                        resultSet.getInt("turn_number"),
                        resultSet.getBoolean("isPlaying"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public Long updateState(final ChessManager chessManager, final Long gameId) {
        final String query =
                "UPDATE state SET turn_owner=?, turn_number=?, isPlaying=? WHERE gameId=?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, chessManager.turnOwner().name());
            pstmt.setInt(2, chessManager.turnNumber());
            pstmt.setBoolean(3, chessManager.isPlaying());
            pstmt.setInt(4, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
