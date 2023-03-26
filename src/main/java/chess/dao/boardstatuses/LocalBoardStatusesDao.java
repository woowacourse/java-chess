package chess.dao.boardstatuses;

import chess.domain.Camp;
import chess.dto.ChessBoardStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocalBoardStatusesDao implements BoardStatusesDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Integer> findAllNotOverIds() {
        final String sql = "SELECT board_id FROM board_statuses WHERE isOver = 'N'";

        List<Integer> ids = new ArrayList<>();
        try (final Connection connection = getConnection();
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
        final String sql = "SELECT current_turn, isOver FROM board_statuses WHERE board_id = ?";

        try (final Connection connection = getConnection();
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
        final String sql = "INSERT INTO board_statuses (board_id, current_turn, isOver)"
                + "VALUES (?, ?, ?)"
                + "ON DUPLICATE KEY UPDATE current_turn = ?, isOver = ?;";

        try (final Connection connection = getConnection();
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

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
