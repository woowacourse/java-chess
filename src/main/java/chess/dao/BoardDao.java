package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.Piece;
import chess.domain.piece.SavePieceGenerator;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private int piece_id;

    public BoardDao() {
        piece_id = 1;
    }

    private Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Map<Square, Piece> board, int board_id) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (piece_id, board_id, type, team, square) values (?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            removeBoard(board_id, connection, statement);
            List<Map.Entry<Square, Piece>> pieces = board.entrySet().stream()
                .filter(entry -> !entry.getValue().isNone())
                .collect(Collectors.toList());
            statement = connection.prepareStatement(sql);
            for (Map.Entry<Square, Piece> entry : pieces) {
                Square square = entry.getKey();
                Piece piece = entry.getValue();
                setStatement(board_id, statement, square, piece);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStatement(int board_id, PreparedStatement statement, Square square, Piece piece) throws
        SQLException {
        statement.setInt(1, piece_id++);
        statement.setInt(2, board_id);
        statement.setString(3, piece.getType());
        statement.setString(4, piece.getColor());
        statement.setString(5, square.getName());
    }

    private void removeBoard(int board_id, Connection connection, PreparedStatement statement) throws SQLException {
        final String sql = "delete from piece where board_id = ?";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public Board find(int board_id) {
        final Connection connection = getConnection();
        final String sql = "select piece_id, board_id, type, team, square from piece where board_id = ?";
        final Map<Square, Piece> board = new HashMap<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, board_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Square square = new Square(resultSet.getString("square"));
                Piece piece = SavePieceGenerator.generatePiece(resultSet.getString("type"),
                    resultSet.getString("team"));
                board.put(square, piece);
            }
            for (Rank rank : Rank.values()) {
                for (File file : File.values()) {
                    board.putIfAbsent(new Square(file, rank), new None(Color.NONE));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Board(board);
    }
}
