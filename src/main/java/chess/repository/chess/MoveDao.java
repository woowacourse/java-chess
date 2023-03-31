package chess.repository.chess;

import chess.mysql.JdbcTemplate;
import chess.mysql.RowMapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoveDao {

    private final JdbcTemplate jdbcTemplate;

    public MoveDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(int boardId, String origin, String destination, int turn) {
        String query = "INSERT INTO move (board_id, origin, destination, turn) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.save(query, boardId, origin, destination, turn);
    }

    public List<MoveDto> findMovesByBoardId(int boardId) {
        String query = "SELECT board_id, origin, destination, turn FROM move WHERE board_id = ?";
        List<MoveDto> moves = jdbcTemplate.query(query, movesMapper(), boardId);
        moves.sort(Comparator.comparingInt(MoveDto::getTurn));
        return moves;
    }

    private RowMapper<List<MoveDto>> movesMapper() {
        return resultSet -> {
            List<MoveDto> moves = new ArrayList<>();
            while (resultSet.next()) {
                MoveDto moveDto = MoveDto.of(
                        resultSet.getInt("board_id"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getInt("turn")
                );
                moves.add(moveDto);
            }
            return moves;
        };
    }

    public void deleteByBoardId(int boardId) {
        String query = "DELETE FROM move WHERE board_id = ?";
        jdbcTemplate.executeUpdate(query, boardId);
    }
}
