package chess.repository.chess;

import chess.domain.game.state.GameState;
import chess.mysql.JdbcTemplate;
import chess.mysql.RowMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(int userId, GameState gameState) {
        String query = "INSERT INTO board (user_id, status) VALUES (?, ?)";
        return jdbcTemplate.save(query, userId, gameState.getStateName());
    }

    public List<Integer> findBoardIdsByUserId(int userId) {
        String query = "SELECT id FROM board WHERE user_id = ?";
        return jdbcTemplate.query(query, boardIdsMapper(), userId);
    }

    private RowMapper<List<Integer>> boardIdsMapper() {
        return resultSet -> {
            List<Integer> boardIds = new ArrayList<>();
            while (resultSet.next()) {
                boardIds.add(resultSet.getInt("id"));
            }
            return boardIds;
        };
    }

    public void delete(int boardId) {
        String query = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.executeUpdate(query, boardId);
    }

    public void update(int boardId, GameState gameState) {
        String query = "UPDATE board SET status = ? WHERE id = ?";
        jdbcTemplate.executeUpdate(query, gameState.getStateName(), boardId);
    }

    public Optional<String> findStatusByBoardId(int boardId) {
        String query = "SELECT status FROM board WHERE id = ?";
        return jdbcTemplate.query(query, statusMapper(), boardId);
    }

    private RowMapper<Optional<String>> statusMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("status"));
            }
            return Optional.empty();
        };
    }
}
