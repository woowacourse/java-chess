package chess.database.dao;

import chess.database.DBConnector;
import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.square.Square;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {
    private static ChessBoardDao INSTANCE = new ChessBoardDao();
    private final Connection connection;

    public static ChessBoardDao getInstance() {
        if (INSTANCE == null) return new ChessBoardDao();
        return INSTANCE;
    }

    private ChessBoardDao() {
        this.connection = DBConnector.getConnection();
    }

    public ChessBoard load() throws SQLException {
        final String sql = "SELECT square, piece FROM chessboard";
        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {
            ChessBoard chessBoard = new ChessBoard();
            Map<Square, Piece> savedBoard = new HashMap<>();
            while (rs.next()) {
                String square = rs.getString("square");
                String piece = rs.getString("piece");
                savedBoard.put(Square.of(square), PieceFactory.of(piece));
                chessBoard.updateChessBoard(savedBoard);
            }
            return chessBoard;
        }
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM chessboard";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void save(Square square, Piece piece) throws SQLException {
        String sql = "INSERT INTO chessboard(square, piece) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, square.toString());
        statement.setString(2, piece.toString());
        statement.executeUpdate();
    }
}
