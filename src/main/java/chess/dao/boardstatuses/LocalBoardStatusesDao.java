package chess.dao.boardstatuses;

import chess.dao.JdbcTemplate;
import chess.domain.Camp;
import chess.dto.ChessBoardStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LocalBoardStatusesDao implements BoardStatusesDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Integer> findAvailableBoardIds() {
        final String query = "SELECT board_id FROM board_statuses WHERE is_over = 'N'";

        return jdbcTemplate.executeQuery(query, Collections.emptyList(), resultSet -> {
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            return ids;
        });
    }

    @Override
    public Optional<ChessBoardStatus> find(final int boardId) {
        final String query = "SELECT current_turn, is_over FROM board_statuses WHERE board_id = ? AND is_over = 'N'";

        final ChessBoardStatus result = jdbcTemplate.executeQuery(query, List.of(boardId), resultSet -> {
            if (resultSet.next()) {
                return new ChessBoardStatus(
                        Camp.valueOf(resultSet.getString(1)), convertIsOver(resultSet.getString(2))
                );
            }
            return null;
        });

        return Optional.ofNullable(result);
    }

    @Override
    public void insertOrUpdate(final int boardId, final ChessBoardStatus status) {
        final String query = "INSERT INTO board_statuses (board_id, current_turn, is_over)"
                + "VALUES (?, ?, ?)"
                + "ON DUPLICATE KEY UPDATE current_turn = ?, is_over = ?";

        final String currentTurn = status.getCurrentTurn().name();
        final String isOver = convertIsOver(status.isOver());

        jdbcTemplate.executeUpdate(query, List.of(
                boardId, currentTurn, isOver,
                currentTurn, isOver
        ));
    }

    private boolean convertIsOver(String result) {
        return "Y".equals(result);
    }

    private String convertIsOver(boolean isOver) {
        if (isOver) {
            return "Y";
        }
        return "N";
    }

    @Override
    public void delete(final int boardId) {
        final String query = "DELETE FROM board_statuses WHERE board_id = ?";

        jdbcTemplate.executeUpdate(query, List.of(boardId));
    }

}
