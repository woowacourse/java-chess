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

public class ChessDao {

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

    public void insertBoardSquares(List<SquareDto> squares) {
        for (SquareDto square : squares) {
            insertBoardSquaresWithPositionAndPiece(square);
        }
    }

    private void insertBoardSquaresWithPositionAndPiece(SquareDto square) {
        Position position = square.getPosition();
        if (square.getPiece() == null) {
            insertBoardSquare(position.getRankAndFile(), null, null);
            return;
        }
        Piece piece = square.getPiece();
        insertBoardSquare(position.getRankAndFile(), piece.getClass().getSimpleName(), piece.getColor().name());
    }

    private void insertBoardSquare(String position, String piece, String color) {
        final Connection connection = getConnection();
        final String query = "INSERT INTO board(position, piece, color) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBoardSquare(String position, String piece, String color) {
        final Connection connection = getConnection();
        final String query = "UPDATE board SET piece = ? , color = ? WHERE position=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, piece);
            statement.setString(2, color);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SquareDto> getBoardSquares() {
        final Connection connection = getConnection();
        final String query = "SELECT * FROM board";
        List<SquareDto> squares = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addSquare(squares, resultSet);
            }
            return squares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addSquare(List<SquareDto> squares, ResultSet resultSet) throws SQLException {
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

    public void insertState(String color) {
        final Connection connection = getConnection();
        final String query = "INSERT INTO state VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllState() {
        final Connection connection = getConnection();
        final String query = "DELETE FROM state";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getState() {
        final Connection connection = getConnection();
        final String query = "SELECT * FROM state LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("color");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBoard() {
        final Connection connection = getConnection();
        final String query = "DELETE FROM board";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
