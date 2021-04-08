package chess.dao;

import chess.controller.dto.PieceDto;
import chess.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final DBConnection dbConnection = DBConnection.getInstance();

    public void add(String pieceName, String pieceTeam, double pieceScore, String piecePosition, String boardName) {
        String sql = "INSERT INTO piece(name, team, score, position, board_name) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, pieceName);
            pstmt.setString(2, pieceTeam);
            pstmt.setDouble(3, pieceScore);
            pstmt.setString(4, piecePosition);
            pstmt.setString(5, boardName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 piece를 추가할 수 없습니다.");
        }
    }

    public void addAll(String boardName, List<PieceDto> pieces) {
        for (PieceDto piece : pieces) {
            add(piece.getName(), piece.getTeamColor(), piece.getScore(), piece.getCurrentPosition(), boardName);
        }
    }

    public void update(String boardName, String currentPosition, String targetPosition) {
        String sql = "UPDATE piece SET position = ? WHERE board_name = ? AND position = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, targetPosition);
            pstmt.setString(2, boardName);
            pstmt.setString(3, currentPosition);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 move update 실패");
        }
    }

    public List<PieceDto> selectAllByBoardName(String boardName) {
        String sql = "SELECT * FROM piece WHERE board_name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<PieceDto> pieces = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString(2);
                    String teamColor = rs.getString(3);
                    double score = rs.getDouble(4);
                    String currentPosition = rs.getString(5);
                    pieces.add(new PieceDto(name, teamColor, score, currentPosition));
                }
                return pieces;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 pieces 탐색 실패");
        }
    }

    public void deleteByBoardName(String boardName) {
        String sql = "DELETE FROM piece WHERE board_name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 piece delete 실패");
        }
    }
}
