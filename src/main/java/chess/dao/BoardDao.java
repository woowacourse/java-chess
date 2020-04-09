package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao extends Dao {
    public void addPiece(Position position, Piece piece) {
        try {
            String query = "INSERT INTO board VALUES (?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, position.name());
            pstmt.setString(2, piece.chessPiece());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void addBoard(Map<Position, Piece> board) {
        for (Position position : board.keySet()) {
            this.addPiece(position, board.get(position));
        }
    }

    public void updateBoard(String position, String piece) {
        try {
            String query = "UPDATE board SET piece = ? where position = ?";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, piece);
            pstmt.setString(2, position);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }


    public Map<String, String> showPieces() {
        try {
            String query = "SELECT * FROM board";
            PreparedStatement pstmt = getConnection().prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            Map<String, String> board = new HashMap<>();
            while (rs.next()) {
                board.put(rs.getString("position"), rs.getString("piece"));
            }
            return board;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void clearBoard() {
        try {
            String query = "TRUNCATE board";
            PreparedStatement pstmt = getConnection().prepareStatement(query);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}