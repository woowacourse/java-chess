package chess.db;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.util.JdbcTemplate;
import chess.util.RowMapper;

import java.sql.SQLException;
import java.util.Optional;

public class MoveHistoryDao {
    public void addMoveHistory(String gameId, PieceColor team, Position source, Position target) throws SQLException {
        String query = "INSERT INTO move_history (game_id, moves, team, source_position, target_position) " +
                "VALUES (?, (SELECT IFNULL(MAX(moves) + 1, 1) FROM move_history AS INNERTABLE WHERE game_id = ?), ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.executeUpdate(query, gameId, gameId, team.name(), source.name(), target.name());
    }

    public Optional<String> figureLastTurn(String gameId) throws SQLException {
        String query = "SELECT team FROM move_history WHERE game_id = ? ORDER BY moves DESC LIMIT 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        RowMapper<Optional<String>> rowMapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(rs.getString("team"));
        };

        return jdbcTemplate.executeQuery(query, rowMapper, gameId);
    }

    public void deleteMoveHistory(String gameId) throws SQLException {
        String query = "DELETE FROM move_history WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.executeUpdate(query, gameId);
    }
}
