package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.DBConnector;
import chess.domain.piece.Color;
import chess.domain.piece.InitialPositionPieceGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.domain.position.Square;

public class PieceDao {
    private static final String ERROR_MESSAGE_NO_SAVED_GAME = "헉.. 저장 안한거 아냐? 그런 게임은 없어!";
    private final DBConnector dbConnector;

    public PieceDao() {
        this.dbConnector = new DBConnector();
    }

    public void save(String gameID) {
        String sql = "insert into piece (position, type, color, gameID) values (?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            insertPieces(gameID, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertPieces(String gameID, PreparedStatement statement) throws SQLException {
        for (Column column : Column.values()) {
            insertPiecesInRow(gameID, statement, column);
        }
    }

    private void insertPiecesInRow(String gameID, PreparedStatement statement, Column column) throws SQLException {
        for (Row row : Row.values()) {
            statement.setString(1, new Square(column, row).getName());
            statement.setString(2, InitialPositionPieceGenerator.getType(column, row).name());
            statement.setString(3, InitialPositionPieceGenerator.getColor(row).name());
            statement.setString(4, gameID);
            statement.executeUpdate();
        }
    }

    public void deleteByPosition(Square target) {
        String sql = "delete from piece where position = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, target.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(Square source, Square target) {
        String sql = "update piece set position = ? where position = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, target.getName());
            statement.setString(2, source.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNone(String gameID, Square source) {
        String sql = "insert into piece (position, type, color, gameID) values (?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, source.getName());
            statement.setString(2, InitialPositionPieceGenerator.NONE.name());
            statement.setString(3, Color.NONE.name());
            statement.setString(4, gameID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(String gameID) {
        String sql = "delete from piece where gameID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Square, Piece> findByGameID(String gameID) {
        String sql = "select position, type, color from piece where gameID = ?";
        Map<Square, Piece> board = new HashMap<>();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = getPreparedStatement(gameID, sql, connection);
             ResultSet resultSet = statement.executeQuery()) {
            initBoard(board, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        checkGameExist(board);
        return board;
    }

    private PreparedStatement getPreparedStatement(String gameID, String sql, Connection connection) throws
            SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, gameID);
        return statement;
    }

    private void initBoard(Map<Square, Piece> board, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String position = resultSet.getString("position");
            String type = resultSet.getString("type");
            String color = resultSet.getString("color");
            board.put(new Square(position), Piece.createByTypeAndColor(type, color));
        }
    }

    private void checkGameExist(Map<Square, Piece> board) {
        if (board.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NO_SAVED_GAME);
        }
    }
}
