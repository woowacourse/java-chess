package chess.db;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.util.JdbcTemplate;
import chess.util.ParameterSetter;
import chess.util.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MoveHistoryDao {
    public void addMoveHistory(String gameId, PieceColor team, Position source, Position target) throws SQLException {
        String query = "INSERT INTO move_history (game_id, moves, team, source_position, target_position) " +
                "VALUES (?, (SELECT IFNULL(MAX(moves) + 1, 1) FROM move_history AS INNERTABLE WHERE game_id = ?), ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
                pstmt.setString(2, gameId);
                pstmt.setString(3, team.name());
                pstmt.setString(4, source.name());
                pstmt.setString(5, target.name());
            }
        };

        jdbcTemplate.executeUpdate(query, parameterSetter);
    }

    public Optional<String> figureLastTurn(String gameId) throws SQLException {
        String query = "SELECT team FROM move_history WHERE game_id = ? ORDER BY moves DESC LIMIT 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
            }
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.ofNullable(rs.getString("team"));
            }
        };

        return (Optional<String>) jdbcTemplate.executeQuery(query, parameterSetter, rowMapper);
    }

    public void deleteMoveHistory(String gameId) throws SQLException {
        String query = "DELETE FROM move_history WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
            }
        };

        jdbcTemplate.executeUpdate(query, parameterSetter);
    }

}
