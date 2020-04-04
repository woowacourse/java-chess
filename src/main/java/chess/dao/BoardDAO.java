package chess.dao;

import static chess.util.RepositoryUtil.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.GamePiece;
import chess.domain.player.User;

public class BoardDAO {

    public void addBoard(Board board, User first, User second) throws SQLException {
        String query = "INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        pstmt.setString(3, String.valueOf(board.getTurn()));

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        CellDAO cellDAO = new CellDAO();
        cellDAO.addCells(board, boardId);
    }

    public Board findByUserName(User first, User second) throws SQLException {
        String query = "SELECT * FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return null;

        int boardId = rs.getInt(1);

        CellDAO cellDAO = new CellDAO();

        return BoardFactory.of(cellDAO.findByBoardId(boardId), rs.getInt(4));
    }
}
