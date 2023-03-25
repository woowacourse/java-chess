package chess.repository;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.RowMapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoveDao {

    private final JdbcTemplate jdbcTemplate;

    public MoveDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(int boardId, String origin, String destination, int turn) {
        String query = "INSERT INTO move (chess_id, origin, destination, turn) VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(query, boardId, origin, destination, turn);
    }

    public List<List<String>> findMovesByBoardId(int boardId) {
        String query = "SELECT origin, destination, turn FROM move WHERE chess_id = ?";
        List<List<String>> moves = jdbcTemplate.query(query, movesMapper(), boardId);
        moves.sort(Comparator.comparingInt(move -> Integer.parseInt(move.get(2))));
        return moves;
    }

    private RowMapper<List<List<String>>> movesMapper() {
        return resultSet -> {
            List<List<String>> moves = new ArrayList<>();
            while (resultSet.next()) {
                List<String> move = new ArrayList<>();
                move.add(resultSet.getString("origin"));
                move.add(resultSet.getString("destination"));
                move.add(resultSet.getString("turn"));
                moves.add(move);
            }
            return moves;
        };
    }

    public void deleteByBoardId(int boardId) {
        String query = "DELETE FROM move WHERE chess_id = ?";
        jdbcTemplate.executeUpdate(query, boardId);
    }
}
