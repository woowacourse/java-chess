package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Symbol;
import chess.domain.position.Column;
import chess.domain.position.NeoPosition;
import chess.domain.position.Row;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

    public Map<NeoPosition, NeoPiece> findAllPositionsAndPieces(int boardId) {
        final Connection connection = connectionManager.getConnection();
        final String sql = "select po.id as po_id, po.position_column, po.position_row, po.board_id, " +
                "pi.id as pi_id, pi.type, pi.color, pi.position_id " +
                "from neo_position po " +
                "inner join neo_piece pi on po.id = pi.position_id " +
                "where board_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            Map<NeoPosition, NeoPiece> all = new HashMap<>();
            while (resultSet.next()) {
                all.put(new NeoPosition(
                                resultSet.getInt("po_id"),
                                Column.findColumn(resultSet.getInt("position_column")),
                                Row.findRow(resultSet.getInt("position_row")),
                                resultSet.getInt("board_id")),
                        new NeoPiece(
                                resultSet.getInt("pi_id"),
                                Symbol.findSymbol(resultSet.getString("type")).type(),
                                Color.findColor(resultSet.getString("color")),
                                resultSet.getInt("position_id")
                        ));
            }
            connectionManager.close(connection);
            return all;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
