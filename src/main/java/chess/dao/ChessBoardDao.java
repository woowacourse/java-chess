package chess.dao;

import chess.dto.ChessBoardDto;
import chess.dto.Tile;
import chess.domain.chesspiece.ChessPieceFactory;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessBoardDao {

    public void saveChessBoard(ChessBoardDto chessBoardDto) throws SQLException {
        Objects.requireNonNull(chessBoardDto);
        Connection connection = new ConnectionDao().getConnection();

        String query = "INSERT INTO chessboard VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (Tile tile : chessBoardDto.getTiles()) {
            pstmt.setString(1, tile.getPosition());
            pstmt.setString(2, tile.getPiece());
            pstmt.executeUpdate();
        }

        pstmt.close();
        connection.close();
    }

    public void deleteChessBoard() throws SQLException {
        Connection connection = new ConnectionDao().getConnection();
        Statement stmt = connection.createStatement();

        String query = "DELETE FROM chessboard";
        stmt.executeUpdate(query);

        stmt.close();
        connection.close();
    }

    public Map<Position, Piece> getChessBoard() throws SQLException {
        Connection connection = new ConnectionDao().getConnection();
        Statement stmt = connection.createStatement();

        String query = "SELECT * FROM chessboard";
        ResultSet rs = stmt.executeQuery(query);

        Map<Position, Piece> chessBoard = new HashMap<>();
        while (rs.next()) {
            Position position = Positions.of(rs.getString("position"));
            Piece piece = ChessPieceFactory.of(rs.getString("piece"));
            chessBoard.put(position, piece);
        }
        stmt.close();
        connection.close();
        return chessBoard;
    }

    public boolean isChessBoardExists() throws SQLException {
        Connection connection = new ConnectionDao().getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM chessBoard";
        ResultSet rs = stmt.executeQuery(query);

        boolean result = rs.next();
        connection.close();
        return result;
    }
}