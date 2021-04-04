package chess.dao;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardDao {

    private final DBConnection dbConnection = DBConnection.getInstance();

    public int addBoard(int boardSize, String turn, boolean isChecked, boolean isKingDead) throws SQLException {
        String sql = "INSERT INTO board(board_size, turn, checked, king_dead) VALUES (?,?,?,?)";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, boardSize);
        pstmt.setString(2, turn);
        pstmt.setBoolean(3, isChecked);
        pstmt.setBoolean(4, isKingDead);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if(!rs.next()){
            throw new IllegalArgumentException("DB에 board를 추가할 수 없습니다.");
        }
        int id = rs.getInt(1);

        rs.close();
        pstmt.close();
        return id;
    }

    public BoardDto selectBoard(List<PieceDto> pieces) throws SQLException {
        String sql = "SELECT * FROM board";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new IllegalArgumentException("Board가 DB에 없습니다.");
        }
        int boardSize = rs.getInt("board_size");
        String turn = rs.getString("turn");
        boolean checked = rs.getBoolean("checked");
        boolean isKingDead = rs.getBoolean("king_dead");
        BoardDto boardDto = new BoardDto(pieces, boardSize, turn, checked, isKingDead);
        rs.close();
        pstmt.close();
        return boardDto;
    }

    public void updateTurn(String turn) throws SQLException {
        String sql = "UPDATE board SET turn = ?";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        pstmt.setString(1, turn);

        if (pstmt.executeUpdate() == 0) {
            throw new IllegalArgumentException("DB에 move update 실패");
        }
        pstmt.close();
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM board";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
