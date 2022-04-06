package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private Connection connection = getConnection();

    public void save(Map<Coordinate, Piece> board, int boardId) {
        final String sql = "INSERT INTO piece (boardId, coordinate, symbol, team) VALUES (?, ?, ?, ?)";
        board.forEach((key, value) -> savePiece(sql, boardId, key, value));
    }

    public void savePiece(String sql, int boardId, Coordinate coordinate, Piece piece) {
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, coordinate.getColumn().getName() + coordinate.getRow().getValue());
            statement.setString(3, piece.getSymbol().toUpperCase());
            statement.setString(4, piece.getTeam().getName());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Coordinate, Piece> findByGameId(String id) {
        String sql = "SELECT * FROM piece WHERE boardId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            Map<Coordinate, Piece> board = new LinkedHashMap<>();
            while (resultSet.next()) {
                String coordinate = resultSet.getString("coordinate");
                String symbol = resultSet.getString("symbol");
                String team = resultSet.getString("team");
                board.put(Coordinate.of(coordinate), PieceFactory.of(team, symbol));
            }
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void updatePosition(String id, String coordinate, Piece piece) {
        String sql = "UPDATE piece "
            + "set symbol = ?, team = ? "
            + "where boardId = ? and coordinate = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, piece.getSymbol().toUpperCase());
            statement.setString(2, piece.getTeam().getName());
            statement.setString(3, id);
            statement.setString(4, coordinate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}