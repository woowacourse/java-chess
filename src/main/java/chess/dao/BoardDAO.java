package chess.dao;

import static chess.util.DBConnector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.player.User;
import chess.util.DBConnector;

public class BoardDAO {

    private CellDAO cellDAO;
    private DBConnector dbConnector;

    public BoardDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.cellDAO = new CellDAO(dbConnector);
    }

    public void addBoard(Board board, User first, User second) throws SQLException {
        dbConnector.executeUpdate("INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)", first.getName(), second.getName(), String.valueOf(board.getTurn()));

        ResultSet rs = dbConnector.executeQuery("SELECT * FROM board WHERE user1 = ? AND user2 = ?", first.getName(), second.getName());

        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        cellDAO.addCells(board, boardId);
    }

    public Optional<Board> findBoardByUser(User first, User second) throws SQLException {
        ResultSet rs = dbConnector.executeQuery("SELECT * FROM board WHERE user1 = ? AND user2 = ?", first.getName(), second.getName());

        if (!rs.next())
            return Optional.empty();

        int boardId = rs.getInt(1);

        return Optional.ofNullable(BoardFactory.of(cellDAO.findCellsByBoardId(boardId), rs.getInt(4), first, second));
    }

    public void saveBoardByUserName(Board board, User first, User second) throws SQLException {
        dbConnector.executeUpdate("UPDATE board SET turn = ? WHERE user1 = ? AND user2 = ?", String.valueOf(board.getTurn()), first.getName(), second.getName());

        ResultSet rs = dbConnector.executeQuery("SELECT id FROM board WHERE user1 = ? AND user2 = ?", first.getName(), second.getName());

        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        cellDAO.addCells(board, boardId);
    }

    public boolean deleteBoardByUser(User first, User second) throws SQLException {
        ResultSet rs = dbConnector.executeQuery("SELECT id FROM board WHERE user1 = ? AND user2 = ?", first.getName(), second.getName());

        if (!rs.next())
            return false;

        int boardId = rs.getInt(1);

        cellDAO.deleteCellsByUser(boardId);

        dbConnector.executeUpdate("DELETE FROM board WHERE user1 = ? AND user2 = ?", first.getName(), second.getName());

        return findBoardByUser(first, second) == null;
    }
}
