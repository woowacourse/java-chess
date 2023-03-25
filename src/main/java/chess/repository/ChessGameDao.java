package chess.repository;

import chess.domain.game.state.GameState;
import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.RowMapper;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(String userId, GameState gameState) {
        String query = "INSERT INTO chess_board (user_id, status) VALUES (?, ?)";
        return jdbcTemplate.save(query, userId, gameState.getStateName());
    }

    public List<Integer> findBoardIdsByUserId(String userId) {
        String query = "SELECT chess_id FROM chess_board WHERE user_id = ?";
        return jdbcTemplate.query(query, boardIdsMapper(), userId);
    }

    private RowMapper<List<Integer>> boardIdsMapper() {
        return resultSet -> {
            List<Integer> boardIds = new ArrayList<>();
            while (resultSet.next()) {
                boardIds.add(resultSet.getInt("chess_id"));
            }
            return boardIds;
        };
    }

    public void delete(int boardId) {
        String query = "DELETE FROM chess_board WHERE chess_id = ?";
        jdbcTemplate.executeUpdate(query, boardId);
    }

    public void update(int boardId, GameState gameState) {
        String query = "UPDATE chess_board SET status = ? WHERE chess_id = ?";
        jdbcTemplate.executeUpdate(query, gameState.getStateName(), boardId);
    }

    public String findStatusByBoardId(int boardId) {
        String query = "SELECT status FROM chess_board WHERE chess_id = ?";
        return jdbcTemplate.query(query, statusMapper(), boardId);
    }

    private RowMapper<String> statusMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
            return null;
        };
    }

    public String findUserIdByBoardId(int boardId) {
        String query = "SELECT user_id FROM chess_board WHERE chess_id = ?";
        return jdbcTemplate.query(query, userIdMapper(), boardId);
    }

    private RowMapper<String> userIdMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                return resultSet.getString("user_id");
            }
            return null;
        };
    }

}
