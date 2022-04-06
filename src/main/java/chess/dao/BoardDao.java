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

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private Connection connection = getConnection();

    public void save(Map<Coordinate, Piece> board, int gameId) {
        final String sql = "INSERT INTO board (game_id, position, type, color) values (?, ?, ?, ?)";
        board.forEach((key, value) -> savePiece(sql, gameId, key, value));
    }

    public void savePiece(String sql, int gameId, Coordinate coordinate, Piece piece) {
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameId);
            statement.setString(2, coordinate.getColumn().getName() + coordinate.getRow().getValue());
            statement.setString(3, piece.getSymbol().toUpperCase());
            statement.setString(4, piece.getTeam().getName());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Coordinate, Piece> findByGameId(String id) {
        String sql = "SELECT * FROM board WHERE game_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            Map<Coordinate, Piece> board = new LinkedHashMap<>();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String type = resultSet.getString("type");
                String color = resultSet.getString("color");
                board.put(Coordinate.of(position), PieceFactory.of(color, type));
            }
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void updatePosition(String id, String coordinate, Piece piece) {
        String sql = "UPDATE board "
            + "set type = ?, color = ? "
            + "where game_id = ? and position = ?";

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