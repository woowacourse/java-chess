package chess.repository;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.RowMapper;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(String userId) {
        String query = "INSERT INTO chess_board (user_id) VALUES (?)";
        return jdbcTemplate.save(query, userId);
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
}
