package chess.db;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.util.JdbcTemplate;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MoveHistoryDao {
    public void addMoveHistory(String gameId, PieceColor team, Position source, Position target) {
        String query = "INSERT INTO move_history (game_id, moves, team, source_position, target_position) " +
                "VALUES (?, (SELECT IFNULL(MAX(moves) + 1, 1) FROM move_history AS INNERTABLE WHERE game_id = ?), ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
                pstmt.setString(2, gameId);
                pstmt.setString(3, team.name());
                pstmt.setString(4, source.name());
                pstmt.setString(5, target.name());
            }
        };

        jdbcTemplate.executeUpdate(query);
    }

    public Optional<String> figureLastTurn(String gameId) {
        String query = "SELECT team FROM move_history WHERE game_id = ? ORDER BY moves DESC LIMIT 1";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gameId);
            rs = pstmt.executeQuery();
            rs.next();
            return Optional.ofNullable(rs.getString("team"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    public void deleteMoveHistory(String gameId) {
        String query = "DELETE FROM move_history WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
            }
        };

        jdbcTemplate.executeUpdate(query);
    }

}
