package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Symbol;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionDao {

    private final ConnectionManager connectionManager;

    public PositionDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Position save(Position position) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into position (position_column, position_row, board_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, position.getColumn().value());
            preparedStatement.setInt(2, position.getRow().value());
            preparedStatement.setInt(3, position.getBoardId());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("실행에 오류가 생겼습니다");
            }
            return new Position(generatedKeys.getInt(1), position.getColumn(), position.getRow(), position.getBoardId());
        });
    }

    public Position getByColumnAndRowAndBoardId(Column column, Row row, int boardId) {
        return connectionManager.executeQuery(connection -> {
            final ResultSet resultSet = findPosition(column, row, boardId, connection);
            return new Position(
                    resultSet.getInt("id"),
                    Column.findColumn(resultSet.getInt("position_column")),
                    Row.findRow(resultSet.getInt("position_row")),
                    resultSet.getInt("board_id")
            );
        });
    }

    private ResultSet findPosition(Column column, Row row, int boardId, Connection connection) throws SQLException {
        final String sql = "select np.id, np.position_column, np.position_row, np.board_id " +
                "from position as np " +
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

    public int saveAllPosition(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into position (position_column, position_row, board_id) values (?, ?, ?)";
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
            return preparedStatement.executeBatch().length;
        });
    }

    public int getPositionIdByColumnAndRowAndBoardId(Column column, Row row, int boardId) {
        return getByColumnAndRowAndBoardId(column, row, boardId).getId();
    }

    public Map<Position, Piece> findAllPositionsAndPieces(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select po.id as po_id, po.position_column, po.position_row, po.board_id, " +
                    "pi.id as pi_id, pi.type, pi.color, pi.position_id " +
                    "from position po " +
                    "inner join piece pi on po.id = pi.position_id " +
                    "where board_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> existPiecesWithPosition = new HashMap<>();
            while (resultSet.next()) {
                existPiecesWithPosition.put(makePosition(resultSet), makePiece(resultSet));
            }
            return existPiecesWithPosition;
        });
    }

    private Position makePosition(ResultSet resultSet) throws SQLException {
        return new Position(
                resultSet.getInt("po_id"),
                Column.findColumn(resultSet.getInt("position_column")),
                Row.findRow(resultSet.getInt("position_row")),
                resultSet.getInt("board_id"));
    }

    private Piece makePiece(ResultSet resultSet) throws SQLException {
        return new Piece(
                resultSet.getInt("pi_id"),
                Color.findColor(resultSet.getString("color")),
                Symbol.findSymbol(resultSet.getString("type")).type(),
                resultSet.getInt("position_id")
        );
    }

    public List<Position> getPaths(List<Position> positions, int roomId) {
        List<Position> realPositions = new ArrayList<>();
        for (Position position : positions) {
            realPositions.add(getByColumnAndRowAndBoardId(position.getColumn(), position.getRow(), roomId));
        }
        return realPositions;
    }
}
