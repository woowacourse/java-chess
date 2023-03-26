package chess.dao.boardstatuses;

import chess.dao.ConnectionManager;
import chess.domain.Camp;
import chess.dto.ChessBoardStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocalBoardStatusesDao implements BoardStatusesDao {

    public List<Integer> findAvailableBoardIds() {
        final String sql = "SELECT board_id FROM board_statuses WHERE is_over = 'N'";

        List<Integer> ids = new ArrayList<>();
        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ChessBoardStatus> find(final int boardId) {
        final String sql = "SELECT current_turn, is_over FROM board_statuses WHERE board_id = ?";

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Camp currentTurn = Camp.valueOf(resultSet.getString(1));
                boolean isOver = convertIsOver(resultSet.getString(2));
                return Optional.of(new ChessBoardStatus(currentTurn, isOver));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void insertOrUpdate(final int boardId, final ChessBoardStatus status) {
        final String sql = "INSERT INTO board_statuses (board_id, current_turn, is_over)"
                + "VALUES (?, ?, ?)"
                + "ON DUPLICATE KEY UPDATE current_turn = ?, is_over = ?;";

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            String currentTurn = status.getCurrentTurn().name();
            String isOver = convertIsOver(status.isOver());

            preparedStatement.setInt(1, boardId);
            preparedStatement.setString(2, currentTurn);
            preparedStatement.setString(3, isOver);
            preparedStatement.setString(4, currentTurn);
            preparedStatement.setString(5, isOver);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        final String sql = "DELETE FROM board_statuses WHERE board_id = ?";

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
