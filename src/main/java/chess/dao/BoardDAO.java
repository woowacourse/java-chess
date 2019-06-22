package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.AbstractPiece;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardDTO;
import chess.utils.DataParser;
import chess.utils.WebUtils;

public class BoardDAO {
    public static void add(BoardDTO boardDTO) throws SQLException {
        String query = "INSERT board(x, y, piece) VALUES(?, ?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Board board = boardDTO.getBoard();
        for (Position position : board.keySet()) {
            pstmt.setString(1, position.getX());
            pstmt.setString(2, position.getY());
            pstmt.setString(3, WebUtils.caseChanger(board.get(position)));
            pstmt.executeUpdate();
        }
    }


    public static BoardDTO selectAll() throws SQLException {
        String query = "SELECT x, y, piece FROM board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Map<Position, AbstractPiece> queryResult = new HashMap<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            queryResult.put(
                    DataParser.position(rs.getString(1), rs.getString(2)),
                    DataParser.piece(rs.getString(3))
            );
        }
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoard(new Board(queryResult));
        return boardDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(BoardDTO boardDTO) throws SQLException {
        deleteAll();
        add(boardDTO);
    }
}
