package chess.dao.boardstatuses;

import chess.dao.JdbcTemplate;
import chess.domain.Camp;
import chess.dto.ChessBoardStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JdbcBoardStatusesDao implements BoardStatusesDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Integer> findAllNotOverBoardIds() {
        final String query = "SELECT board_id FROM board_statuses WHERE is_over = false";

        return jdbcTemplate.executeQuery(query, Collections.emptyList(), resultSet -> {
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            return ids;
        });
    }

    @Override
    public Optional<ChessBoardStatus> findByBoardId(final int boardId) {
        final String query = "SELECT current_turn, is_over FROM board_statuses WHERE board_id = ? AND is_over = false";

        final ChessBoardStatus result = jdbcTemplate.executeQuery(query, List.of(boardId), resultSet -> {
            if (resultSet.next()) {
                return new ChessBoardStatus(
                        Camp.valueOf(resultSet.getString(1)),
                        resultSet.getBoolean(2)
                );
            }
            return null;
        });

        return Optional.ofNullable(result);
    }

    @Override
    public void insertOrUpdate(final int boardId, final ChessBoardStatus status) {
        final String query = "INSERT INTO board_statuses (board_id, current_turn, is_over) "
                + "VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE current_turn = ?, is_over = ? ";

        final String currentTurn = status.getCurrentTurn().name();
        final int isOver = Boolean.compare(status.isOver(), false);

        jdbcTemplate.executeUpdate(query, List.of(
                boardId, currentTurn, isOver,
                currentTurn, isOver
        ));
    }

    @Override
    public void delete(final int boardId) {
        final String query = "DELETE FROM board_statuses WHERE board_id = ?";

        jdbcTemplate.executeUpdate(query, List.of(boardId));
    }

}
