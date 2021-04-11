package chess.dao;

import chess.controller.web.dto.history.HistoryResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    public Long saveHistory(final Connection connection, final HistoryResponseDto history, final Long gameId) {
        final String query =
                "INSERT INTO history(gameId, move_command, turn_owner, turn_number, isPlaying) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, history.getMoveCommand());
            pstmt.setString(3, history.getTurnOwner());
            pstmt.setInt(4, history.getTurnNumber());
            pstmt.setBoolean(5, history.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new IllegalStateException(sqlException);
            }
            throw new IllegalStateException(e);
        }
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        final String query =
                "SELECT * from history where gameId = ? ORDER BY id ASC";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<HistoryResponseDto> historyResponseDtos = new ArrayList<>();
                while (resultSet.next()) {
                    historyResponseDtos.add(new HistoryResponseDto(
                            resultSet.getString("move_command"),
                            resultSet.getString("turn_owner"),
                            resultSet.getInt("turn_number"),
                            resultSet.getBoolean("isPlaying")
                    ));
                }
                return historyResponseDtos;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
