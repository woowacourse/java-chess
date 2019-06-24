package chess.dao;

import chess.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    private static BoardDao instance;

    private BoardDao() {
    }
    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }

    public void add(BoardDto boardDto) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, boardDto.getPosition());
        pstmt.setString(2, boardDto.getPieceName());

        pstmt.executeUpdate();
    }

    public void addAll(List<BoardDto> boardDtos) throws SQLException {
        for (BoardDto boardDto : boardDtos) {
            add(boardDto);
        }
    }
    
    public List<BoardDto> findAll() throws SQLException {
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<BoardDto> result = new ArrayList<>();
        while(rs.next()) {
            BoardDto boardDto = new BoardDto(rs.getString(1), rs.getString(2));
            result.add(boardDto);
        }

        return result;
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
