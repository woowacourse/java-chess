package chess.dao;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoardDao {

    private final DBConnection dbConnection = DBConnection.getInstance();

    public void add(String boardName, int boardSize, String turn, boolean isChecked, boolean isKingDead) {
        String sql = "INSERT INTO board(name, board_size, turn, checked, king_dead) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.setInt(2, boardSize);
            pstmt.setString(3, turn);
            pstmt.setBoolean(4, isChecked);
            pstmt.setBoolean(5, isKingDead);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Board를 추가할 수 없습니다.");
        }
    }

    public BoardDto selectByName(String boardName) {
        String sql = "SELECT * FROM board WHERE name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<PieceDto> pieces = new PieceDao().selectByBoardName(boardName);
                int boardSize = rs.getInt("board_size");
                String turn = rs.getString("turn");
                boolean checked = rs.getBoolean("checked");
                boolean isKingDead = rs.getBoolean("king_dead");
                String name = rs.getString("name");

                return new BoardDto(pieces, boardSize, turn, checked, isKingDead, name);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Board가 DB에 없습니다.");
        }
    }

    public boolean isNameInBoard(String boardName) {
        String sql = "SELECT * FROM board WHERE name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            if (pstmt.executeUpdate() == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Board가 DB에 없습니다.");
        }
    }

    public void updateTurn(String turn, String boardName) {
        String sql = "UPDATE board SET turn = ? WHERE name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, turn);
            pstmt.setString(2, boardName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 turn update 실패");
        }
    }

    public void deleteByName(String boardName) {
        String sql = "DELETE FROM board WHERE name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 delete 실패");
        }
    }
}
