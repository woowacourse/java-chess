package chess.controller.dao;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.Tile;
import chess.domain.chesspiece.ChessPieceFactory;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Data Access Object
public class ChessBoardDao {
    private Connection connection = new ConnectionDao().getConnection();

    public void saveInitChessBoard(ChessBoardDto chessBoardDto, int roomNumber) throws SQLException {
        String query = "INSERT INTO chessboard VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        for (Tile tile : chessBoardDto.getTiles()) {
            pstmt.setInt(1, roomNumber);
            pstmt.setString(2, tile.getPosition());
            pstmt.setString(3, tile.getPiece());
            pstmt.executeUpdate();
        }
        pstmt.close();
    }

    public void deleteChessBoard(int roomNumber) throws SQLException {
        String query = "DELETE FROM chessboard WHERE room = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public Map<Position, Piece> loadGamePlaying(int roomNumber) throws SQLException {
        String query = "SELECT b.position, b.piece FROM chessboard b ON b.room = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomNumber);

        ResultSet rs = pstmt.executeQuery();
        Map<Position, Piece> chessBoard = new HashMap<>();
        while (rs.next()) {
            chessBoard.put(Positions.of(rs.getString("position")),
                    ChessPieceFactory.of(rs.getString("piece")));
        }
        pstmt.close();

        return chessBoard;
    }
}