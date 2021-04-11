package chess.dao;

import chess.controller.web.dto.score.ScoreResponseDto;
import chess.domain.manager.GameStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreDao {

    public Long saveScore(final Connection connection, final GameStatus gameStatus, final Long gameId) {
        final String query =
                "INSERT INTO score(gameId, white_score, black_score) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId.intValue());
            pstmt.setDouble(2, gameStatus.whiteScore());
            pstmt.setDouble(3, gameStatus.blackScore());
            return pstmt.executeLargeUpdate();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new IllegalStateException("체스 게임의 점수를 저장하는데 실패했습니다.", sqlException);
            }
            throw new IllegalStateException("체스 게임의 점수를 저장하는데 실패했습니다.", e);
        }
    }
    public Long updateScore(final Connection connection, final GameStatus gameStatus, final Long gameId) {
        final String query =
                "UPDATE score SET white_score=?, black_score=? WHERE gameId=?";

        try (PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setDouble(1, gameStatus.whiteScore());
            pstmt.setDouble(2, gameStatus.blackScore());
            pstmt.setInt(3, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new IllegalStateException("체스 게임의 점수를 업데이트하는데 실패했습니다.", sqlException);
            }
            throw new IllegalStateException("체스 게임의 점수를 업데이트하는데 실패했습니다.", e);
        }
    }

    public ScoreResponseDto findScoreByGameId(final Long gameId) {
        final String query =
                "SELECT * from score where gameId = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new ScoreResponseDto(
                        resultSet.getDouble("white_score"),
                        resultSet.getDouble("black_score"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("해당 GameID의 점수를 검색하는데 실패했습니다.", e);
        }
    }
}
