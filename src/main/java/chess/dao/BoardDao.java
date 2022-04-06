package chess.dao;

import java.sql.Connection;
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

    private int piece_id = 1;

    public void save(Map<Square, Piece> board, int board_id, Connection connection) {
        final String sql = "insert into piece (piece_id, board_id, type, team, square) values (?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            List<Map.Entry<Square, Piece>> pieces = board.entrySet().stream()
                .filter(entry -> !entry.getValue().isNone())
                .collect(Collectors.toList());
            for (Map.Entry<Square, Piece> entry : pieces) {
                statement = connection.prepareStatement(sql);
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

    public void remove(int board_id, Connection connection) {
        final String sql = "delete from piece where board_id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, board_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(connection, statement);
    }

    private void close(Connection connection, PreparedStatement statement) {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board find(int board_id, Connection connection) {
        final String sql = "select type, team, square from piece where board_id = ?";
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

        close(connection, statement, resultSet);
        return new Board(board);
    }

    private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
