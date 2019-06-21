package chess.dao;

import chess.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public static void add(BoardDto boardDto) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Map<String, String> board = boardDto.getBoard();
        for (String key : board.keySet()) {
            pstmt.setString(1, key);
            pstmt.setString(2, board.get(key));
            pstmt.executeUpdate();
        }
    }
    
    public static BoardDto findAll() throws SQLException {
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Map<String, String> board = new HashMap<>();
        while(rs.next()) {
            board.put(rs.getString(1), rs.getString(2));
        }

        BoardDto boardDto = new BoardDto();
        boardDto.setBoard(board);
        return boardDto;
    }

    public static void deleteAll() throws SQLException {
        String query = "DELETE FROM baord";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
