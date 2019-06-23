package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.AbstractPiece;
import chess.domain.Board;
import chess.domain.Position;
import chess.dto.BoardDto;
import chess.utils.DataProcessor;
import chess.utils.WebUtils;

public class BoardDao {
    public static void add(BoardDto boardDTO) throws SQLException {
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

    public static BoardDto selectAll() throws SQLException {
        String query = "SELECT x, y, piece FROM board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        Map<Position, AbstractPiece> queryResult = loadData(pstmt);
        return dataLoadedDTO(queryResult);
    }

    private static Map<Position, AbstractPiece> loadData(PreparedStatement pstmt) throws SQLException {
        Map<Position, AbstractPiece> queryResult = new HashMap<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            queryResult.put(
                    DataProcessor.position(rs.getString(1), rs.getString(2)),
                    DataProcessor.piece(rs.getString(3))
            );
        }
        return queryResult;
    }

    private static BoardDto dataLoadedDTO(Map<Position, AbstractPiece> queryResult) {
        BoardDto boardDTO = new BoardDto();
        boardDTO.setBoard(new Board(queryResult));
        return boardDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE board";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(BoardDto boardDTO) throws SQLException {
        deleteAll();
        add(boardDTO);
    }
}
