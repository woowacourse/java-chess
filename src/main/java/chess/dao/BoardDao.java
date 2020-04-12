package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao extends ConnectionManager {
    public void createBoard(Map<Position, Piece> board) {
        for (Position position : board.keySet()) {
            this.addPiece(position, board.get(position));
        }
    }

    private void addPiece(Position position, Piece piece) {
        String query = "INSERT INTO board VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, position.name());
            pstmt.setString(2, piece.chessPiece());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public Map<String, String> readBoard() {
        String query = "SELECT * FROM board";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
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

    public void updateBoard(String position, String piece) {
        String query = "UPDATE board SET piece = ? where position = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, piece);
            pstmt.setString(2, position);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void deleteBoard() {
        String query = "TRUNCATE board";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}