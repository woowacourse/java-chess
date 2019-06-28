package chess.dao;

import chess.domain.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class ChessTurnDao {
    private static final String insertQuery = "INSERT INTO chess_turn (turn) VALUES (?)";
    private static final String updateQuery = "UPDATE chess_turn SET turn=? WHERE game_id=?";
    private static final String selectTurnQuery = "SELECT turn FROM chess_turn WHERE game_id=?";
    private static final String selectMaxQuery = "SELECT max(game_id) FROM chess_turn";
    private static final String deleteQuery = "DELETE FROM chess_turn WHERE game_id=?";
    private static final String selectGameIdsQuery = "SELECT game_id FROM chess_turn";
    private static ChessTurnDao chessTurnDAO;

    private ChessTurnDao() {
    }

    public static ChessTurnDao getInstance() {
        if (chessTurnDAO == null) {
            chessTurnDAO = new ChessTurnDao();
        }
        return chessTurnDAO;
    }

    public PieceColor selectChessTurn(int id) {
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);
        RowMapper rm = rs -> rs.next() ? PieceColor.valueOf(rs.getString("turn")) : null;
        JdbcTemplate template = new JdbcTemplate();
        return (PieceColor) template.executeQuery(selectTurnQuery, pss, rm);
    }

    public void updateChessTurn(int id, PieceColor turn) {
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, String.valueOf(turn));
            pstmt.setInt(2, id);
        };
        JdbcTemplate template = new JdbcTemplate();
        template.executeUpdate(updateQuery, pss);
    }

    public void insertChessTurn(PieceColor turn) {
        PreparedStatementSetter pss = pstmt -> pstmt.setString(1, String.valueOf(turn));
        JdbcTemplate template = new JdbcTemplate();
        template.executeUpdate(insertQuery, pss);
    }

    public int selectMaxGameId() {
        PreparedStatementSetter pss = pstmt -> {
        };
        RowMapper rm = rs -> rs.next() ? rs.getInt("max(game_id)") : -1;
        JdbcTemplate template = new JdbcTemplate();
        return (int) template.executeQuery(selectMaxQuery, pss, rm);
    }

    public void deleteChessTurn(int id) {
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);
        JdbcTemplate template = new JdbcTemplate();
        template.executeUpdate(deleteQuery, pss);
    }

    public List<Integer> selectChessGames() {
        PreparedStatementSetter pss = pstmt -> {
        };
        RowMapper rm = rs -> {
            List<Integer> gameIds = new ArrayList<>();
            while (rs.next()) {
                gameIds.add(rs.getInt("game_id"));
            }
            return gameIds;
        };
        JdbcTemplate template = new JdbcTemplate();
        return (List<Integer>) template.executeQuery(selectGameIdsQuery, pss, rm);
    }
}
