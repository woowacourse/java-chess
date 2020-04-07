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

    private CellDAO cellDAO;

    public BoardDAO() {
        this.cellDAO = new CellDAO();
    }

    public void addBoard(Board board, User first, User second) throws SQLException {
        String query = "INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        pstmt.setString(3, String.valueOf(board.getTurn()));

        pstmt.executeUpdate();
    }

    public Optional<Board> findBoardByUser(User first, User second) throws SQLException {
        String query = "SELECT * FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return Optional.empty();

        int boardId = rs.getInt(1);

        return Optional.ofNullable(BoardFactory.of(cellDAO.findCellsByBoardId(boardId), rs.getInt(4), first, second));
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

        cellDAO.addCells(board, boardId);
    }

    public boolean deleteBoardByUser(User first, User second) throws SQLException {
        String query = "SELECT id FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return false;

        int boardId = rs.getInt(1);

        cellDAO.deleteCellsByUser(boardId);

        query = "DELETE FROM board WHERE user1 = ? AND user2 = ?";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, first.getName());
        pstmt.setString(2, second.getName());
        pstmt.executeUpdate();

        return findBoardByUser(first, second) == null;
    }
}
