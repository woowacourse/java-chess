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
        final Connection connection = connectionManager.getConnection();
        try {
            final ResultSet resultSet = findPosition(column, row, boardId, connection);
            if (!resultSet.next()) {
                return null;
            }
            final NeoPosition neoPosition = new NeoPosition(
                    resultSet.getInt("id"),
                    Column.findColumn(resultSet.getInt("position_column")),
                    Row.findRow(resultSet.getInt("position_row")),
                    resultSet.getInt("board_id")
            );
            connectionManager.close(connection);
            return neoPosition;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet findPosition(Column column, Row row, int boardId, Connection connection) throws SQLException {
        final String sql = "select np.id, np.position_column, np.position_row, np.board_id " +
                "from neo_position as np " +
                "where np.position_column=? and np.position_row=? and np.board_id=?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, column.value());
        preparedStatement.setInt(2, row.value());
        preparedStatement.setInt(3, boardId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public int findPositionIdByColumnAndRowAndBoardId(Connection connection, Column column, Row row, int boardId) {
        try {
            final ResultSet resultSet = findPosition(column, row, boardId, connection);
            if (!resultSet.next()) {
                throw new IllegalArgumentException("위치를 찾을 수 없습니다.");
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllPosition(Connection connection, int boardId) {
        final String sql = "insert into neo_position (position_column, position_row, board_id) values (?, ?, ?)";
        final PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (Column column : Column.values()) {
                for (Row row : Row.values()) {
                    preparedStatement.setInt(1, column.value());
                    preparedStatement.setInt(2, row.value());
                    preparedStatement.setInt(3, boardId);
                    preparedStatement.addBatch();
                    preparedStatement.clearParameters();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
