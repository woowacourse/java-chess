package chess.dao;

import static chess.util.RepositoryUtil.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.player.User;

public class BoardDAO {

    public void addBoard(Board board, User first, User second) throws SQLException {
        String query = "INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        pstmt.setString(3, String.valueOf(board.getTurn()));

        pstmt.executeUpdate();
    }

    public Optional<Board> findByUserName(User first, User second) throws SQLException {
        String query = "SELECT * FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return Optional.empty();

        int boardId = rs.getInt(1);

        CellDAO cellDAO = new CellDAO();

        return Optional.ofNullable(BoardFactory.of(cellDAO.findByBoardId(boardId), rs.getInt(4), first, second));
    }

    public void saveBoardByUserName(Board board, User first, User second) throws SQLException {
        String query = "UPDATE board SET turn = ? WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, board.getTurn());
        pstmt.setString(2, first.getName());
        pstmt.setString(3, second.getName());

        pstmt.executeUpdate();

        query = "SELECT id FROM board WHERE user1 = ? AND user2 = ?";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        CellDAO cellDAO = new CellDAO();
        cellDAO.addCells(board, boardId);
    }
}
