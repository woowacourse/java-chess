package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.SquareDto;

public class BoardDao {

    private final String URL = "jdbc:mysql://localhost:3306/chess";
    private final String USER = "user";
    private final String PASSWORD = "password";

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertBoardSquares(final List<SquareDto> squares) {
        for (SquareDto square : squares) {
            insertBoardSquaresWithPositionAndPiece(square);
        }
    }

    private void insertBoardSquaresWithPositionAndPiece(final SquareDto square) {
        final Position position = square.getPosition();
        if (square.getPiece() == null) {
            insertBoardSquare(position.getRankAndFile(), null, null);
            return;
        }
        final Piece piece = square.getPiece();
        insertBoardSquare(position.getRankAndFile(), piece.getClass().getSimpleName(), piece.getColorName());
    }

    private void insertBoardSquare(final String position, final String piece, final String color) {
        final String query = "INSERT INTO board(position, piece, color) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBoardSquare(final String position, final String piece, final String color) {
        final String query = "UPDATE board SET piece = ? , color = ? WHERE position=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piece);
            statement.setString(2, color);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SquareDto> getBoardSquares() {
        final String query = "SELECT * FROM board";
        final List<SquareDto> squares = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                addSquare(squares, resultSet);
            }
            return squares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addSquare(final List<SquareDto> squares, final ResultSet resultSet) throws SQLException {
        if (resultSet.getString("piece") == null) {
            squares.add(new SquareDto(resultSet.getString("position")));
            return;
        }
        squares.add(
            new SquareDto(
                resultSet.getString("position"),
                resultSet.getString("piece"),
                resultSet.getString("color")
            )
        );
    }

    public void deleteBoard() {
        final String query = "DELETE FROM board";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
