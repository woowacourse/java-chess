package chess.dao;

import chess.controller.dto.BoardDto;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao {
    private final Connection conn;
    public BoardDao(final Connection connection) {
        this.conn = connection;
    }

    public void insertBoard(BoardDto board) throws SQLException {
        final String[][] boardAsString = board.getBoard();

        final String query = "INSERT INTO board_status (position,piece) VALUES (?,?);";

        for(Vertical v : Vertical.values()){
            for(Horizontal h : Horizontal.values()){
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, parsePositionAsString(v,h));
                pstmt.setString(2, boardAsString[v.getIndex()-1][h.getIndex()-1]);
                pstmt.executeUpdate();
            }
        }
    }

    // XXX :: Service로 분리하기
    private String parsePositionAsString(Vertical v, Horizontal h){
        return v.name() + h.getIndex();
    }
}
