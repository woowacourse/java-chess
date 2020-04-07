package chess.dao;

import chess.domain.piece.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessResultDAO {

    public void put(String gameId, Map<Color, Double> teamScore)
        throws SQLException, IllegalAccessException {
        if (selectTeamScore(gameId).isEmpty()) {
            insertTeamScore(gameId, teamScore);
        }
        updateTeamScore(gameId, teamScore);
    }

    public void updateTeamScore(String gameId, Map<Color, Double> teamScore) throws SQLException {
        String query = "UPDATE CHESS_RESULT_TB SET BLACK_SCORE = ?, WHITE_SCORE = ? WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setDouble(1, teamScore.get(Color.BLACK));
        pstmt.setDouble(2, teamScore.get(Color.WHITE));
        pstmt.setString(3, gameId);
        pstmt.executeUpdate();
    }

    public void insertTeamScore(String gameId, Map<Color, Double> teamScore) throws SQLException {
        String query = "INSERT INTO CHESS_RESULT_TB(GAME_ID, BLACK_SCORE, WHITE_SCORE) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setDouble(2, teamScore.get(Color.BLACK));
        pstmt.setDouble(3, teamScore.get(Color.WHITE));
        pstmt.executeUpdate();
    }

    public void deleteTeamScore(String gameId) throws SQLException {
        String query = "DELETE FROM CHESS_RESULT_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.executeUpdate();
    }

    public Map<Color, Double> selectTeamScore(String gameId)
        throws SQLException, IllegalAccessException {
        String query = "SELECT BLACK_SCORE, WHITE_SCORE FROM CHESS_RESULT_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        Map<Color, Double> selectTeamScore = new HashMap<>();

        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }

        selectTeamScore.put(Color.BLACK, rs.getDouble("BLACK_SCORE"));
        selectTeamScore.put(Color.WHITE, rs.getDouble("WHITE_SCORE"));
        return selectTeamScore;
    }

}
