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

    public NeoPosition save(NeoPosition neoPosition) {
        ConnectionFunction<Connection, NeoPosition> runnable = connection -> {
            final String sql = "insert into neo_position (position_column, position_row, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, neoPosition.getColumn().value());
            preparedStatement.setInt(2, neoPosition.getRow().value());
            preparedStatement.setInt(3, neoPosition.getBoardId());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("실행에 오류가 생겼습니다");
            }
            return new NeoPosition(generatedKeys.getInt(1), neoPosition.getColumn(), neoPosition.getRow(), neoPosition.getBoardId());
        };
        return connectionManager.run(runnable);
    }

    public NeoPosition findByColumnAndRowAndBoardId(Column column, Row row, int boardId) {
        ConnectionFunction<Connection, NeoPosition> runnable = connection -> {
            final ResultSet resultSet = findPosition(column, row, boardId, connection);
            return new NeoPosition(
                    resultSet.getInt("id"),
                    Column.findColumn(resultSet.getInt("position_column")),
                    Row.findRow(resultSet.getInt("position_row")),
                    resultSet.getInt("board_id")
            );
        };
        return connectionManager.run(runnable);
    }

    public void saveAllPosition(int boardId) {
        ConnectionFunction<Connection, Void> runnable = connection -> {
            final String sql = "insert into neo_position (position_column, position_row, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            return null;
        };
        connectionManager.run(runnable);
    }

    public int findPositionIdByColumnAndRowAndBoardId(Column column, Row row, int boardId) {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            final ResultSet resultSet = findPosition(column, row, boardId, connection);
            return resultSet.getInt("id");
        };
        return connectionManager.run(runnable);
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
        if (!resultSet.next()) {
            throw new IllegalArgumentException("위치가 존재하지 않습니다.");
        }
        return resultSet;
    }

    public Map<NeoPosition, NeoPiece> findAllPositionsAndPieces(int boardId) {
        ConnectionFunction<Connection, Map<NeoPosition, NeoPiece>> runnable = connection -> {
            final String sql = "select po.id as po_id, po.position_column, po.position_row, po.board_id, " +
                    "pi.id as pi_id, pi.type, pi.color, pi.position_id " +
                    "from neo_position po " +
                    "inner join neo_piece pi on po.id = pi.position_id " +
                    "where board_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            Map<NeoPosition, NeoPiece> existPiecesWithPosition = new HashMap<>();
            while (resultSet.next()) {
                existPiecesWithPosition.put(makeNeoPosition(resultSet), makeNeoPiece(resultSet));
            }
            return existPiecesWithPosition;
        };
        return connectionManager.run(runnable);
    }

    private NeoPosition makeNeoPosition(ResultSet resultSet) throws SQLException {
        return new NeoPosition(
                resultSet.getInt("po_id"),
                Column.findColumn(resultSet.getInt("position_column")),
                Row.findRow(resultSet.getInt("position_row")),
                resultSet.getInt("board_id"));
    }

    private NeoPiece makeNeoPiece(ResultSet resultSet) throws SQLException {
        return new NeoPiece(
                resultSet.getInt("pi_id"),
                Symbol.findSymbol(resultSet.getString("type")).type(),
                Color.findColor(resultSet.getString("color")),
                resultSet.getInt("position_id")
        );
    }

    public int deleteAll() {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            String sql = "delete from neo_position";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }
}
