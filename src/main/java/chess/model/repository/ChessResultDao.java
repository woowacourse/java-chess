package chess.model.repository;

import chess.model.domain.piece.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessResultDao extends ChessDB {

    private final static ChessResultDao INSTANCE = new ChessResultDao();

    private ChessResultDao() {
    }

    public static ChessResultDao getInstance() {
        return INSTANCE;
    }

    public void put(String gameId, Map<Color, Double> teamScore) throws SQLException {
        if (selectTeamScore(gameId).isEmpty()) {
            insertTeamScore(gameId, teamScore);
        }
        updateTeamScore(gameId, teamScore);
    }

    public void updateTeamScore(String gameId, Map<Color, Double> teamScore) throws SQLException {
        String query = "UPDATE CHESS_RESULT_TB SET BLACK_SCORE = ?, WHITE_SCORE = ? WHERE GAME_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setDouble(1, teamScore.get(Color.BLACK));
            pstmt.setDouble(2, teamScore.get(Color.WHITE));
            pstmt.setString(3, gameId);
            pstmt.executeUpdate();
        }
    }

    public void insertTeamScore(String gameId, Map<Color, Double> teamScore) throws SQLException {
        String query = "INSERT INTO CHESS_RESULT_TB(GAME_ID, BLACK_SCORE, WHITE_SCORE) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.setDouble(2, teamScore.get(Color.BLACK));
            pstmt.setDouble(3, teamScore.get(Color.WHITE));
            pstmt.executeUpdate();
        }
    }

    public void deleteTeamScore(String gameId) throws SQLException {
        String query = "DELETE FROM CHESS_RESULT_TB WHERE GAME_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        }
    }

    public Map<Color, Double> selectTeamScore(String gameId) throws SQLException {
        String query = "SELECT BLACK_SCORE, WHITE_SCORE FROM CHESS_RESULT_TB WHERE GAME_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            Map<Color, Double> selectTeamScore = new HashMap<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return selectTeamScore;
                }
                selectTeamScore.put(Color.BLACK, rs.getDouble("BLACK_SCORE"));
                selectTeamScore.put(Color.WHITE, rs.getDouble("WHITE_SCORE"));
            }
            return selectTeamScore;
        }
    }

}
