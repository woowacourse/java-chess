package chess.dao;

import chess.domain.position.Column;
import chess.domain.position.NeoPosition;
import chess.domain.position.Row;

import java.sql.*;

public class NeoPositionDao {

    private final ConnectionManager connectionManager;

    public NeoPositionDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(NeoPosition neoPosition) {
        final Connection connection = connectionManager.getConnection();
        try {
            savePosition(connection, neoPosition);
            connectionManager.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Connection connection, NeoPosition neoPosition) {
        try {
            savePosition(connection, neoPosition);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void savePosition(Connection connection, NeoPosition neoPosition) throws SQLException {
        final String sql = "insert into neo_position (position_column, position_row, board_id) values (?, ?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, neoPosition.getColumn().value());
        preparedStatement.setInt(2, neoPosition.getRow().value());
        preparedStatement.setInt(3, neoPosition.getBoardId());
        preparedStatement.executeUpdate();
    }

    public NeoPosition findByColumnAndRowAndBoardId(Column column, Row row, int boardId) {
        final String sql = "select np.id, np.position_column, np.position_row, np.board_id " +
                "from neo_position as np " +
                "where np.position_column=? and np.position_row=? and np.board_id=?";
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, column.value());
            preparedStatement.setInt(2, row.value());
            preparedStatement.setInt(3, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new NeoPosition(
                    resultSet.getInt("id"),
                    Column.findColumn(resultSet.getInt("position_column")),
                    Row.findRow(resultSet.getInt("position_row")),
                    resultSet.getInt("board_id")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findLastPositionId(Connection connection) {
        final String sql = "select id from neo_position order by id desc limit 1";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
