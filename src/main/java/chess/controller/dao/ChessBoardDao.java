package chess.controller.dao;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.TileDto;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.PieceInfo;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {
    private ConnectionDao connectionDao = new ConnectionDao();

    public void saveChessBoard(ChessBoardDto chessBoardDto, int roomNumber) throws SQLException {
        String query = "INSERT INTO chessboard VALUES (?,?,(SELECT piece_id FROM piece WHERE name=? AND player=?))";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (TileDto tile : chessBoardDto.getTiles()) {
                pstmt.setInt(1, roomNumber);
                pstmt.setString(2, tile.getPosition());
                pstmt.setString(3, tile.getPieceName());
                pstmt.setString(4, tile.getPiecePlayer());
                pstmt.executeUpdate();
            }
        }
    }

    public void deleteChessBoard(int roomNumber) throws SQLException {
        String query = "DELETE FROM chessboard WHERE room = ?";

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            pstmt.executeUpdate();
        }
    }

    public Map<Position, Piece> findPlayingChessBoard(int roomNumber) throws SQLException {
        String query = "SELECT a.position, b.name, b.player FROM chessboard a " +
                "INNER JOIN piece b ON a.piece_id = b.piece_id WHERE a.room = ?";
        Map<Position, Piece> chessBoard = new HashMap<>();

        try (Connection connection = connectionDao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                chessBoard.put(Positions.of(rs.getString("position")),
                        PieceInfo.of(rs.getString("name"), rs.getString("player")));
            }
        }

        return chessBoard;
    }
}