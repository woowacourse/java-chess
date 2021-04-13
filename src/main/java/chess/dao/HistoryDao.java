package chess.dao;

import chess.controller.web.dto.history.HistoryResponseDto;
import chess.domain.History;
import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    public Long saveHistory(final History history, final Long gameId) {
        final String query =
                "INSERT INTO history(game_id, move_command, turn_owner, turn_number, playing) VALUES (?, ?, ?, ?, ?)";

        try (final Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, history.moveCommand());
            pstmt.setString(3, history.turnOwner());
            pstmt.setInt(4, history.turnNumber());
            pstmt.setBoolean(5, history.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("체스게임의 기록을 저장하는데 실패했습니다.", e);
        }
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        final String query = "SELECT * from history where game_id = ? ORDER BY id ASC";

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
                            resultSet.getBoolean("playing")
                    ));
                }
                return historyResponseDtos;
            }
        } catch (SQLException e) {
            throw new DataAccessException("해당 GameID의 기록들을 검색하는데 실패했습니다.", e);
        }
    }
}
