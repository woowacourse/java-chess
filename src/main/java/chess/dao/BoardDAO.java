package chess.dao;

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

    public void addBoard(Board board, User blackUser, User whiteUser) throws SQLException {
        dbConnector.executeUpdate("INSERT INTO board (black, white, turn) VALUES (?, ?, ?)", blackUser.getName(),
                whiteUser.getName(), String.valueOf(board.getTurn()));

        ResultSet rs = findBoardResultSet(blackUser, whiteUser);

        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        cellDAO.addCells(board, boardId);

    }

    public Optional<Board> findBoardByUser(User blackUser, User whiteUser) throws SQLException {
        ResultSet rs = findBoardResultSet(blackUser, whiteUser);

        if (!rs.next())
            return Optional.empty();

        int boardId = rs.getInt(1);

        return Optional.ofNullable(
                BoardFactory.of(cellDAO.findCellsByBoardId(boardId), rs.getInt(4), blackUser, whiteUser));
    }

    public void saveBoardByUserName(Board board, User blackUser, User whiteUser) throws SQLException {
        dbConnector.executeUpdate("UPDATE board SET turn = ? WHERE black = ? AND white = ?",
                String.valueOf(board.getTurn()), blackUser.getName(), whiteUser.getName());

        ResultSet rs = findBoardResultSet(blackUser, whiteUser);

        if (!rs.next())
            return;

        int boardId = rs.getInt(1);

        cellDAO.updateCellsByBoardId(board, boardId);
    }

    public boolean deleteBoardByUser(User blackUser, User whiteUser) throws SQLException {
        ResultSet rs = findBoardResultSet(blackUser, whiteUser);

        if (!rs.next())
            return false;

        int boardId = rs.getInt(1);

        cellDAO.deleteCellsByUser(boardId);

        dbConnector.executeUpdate("DELETE FROM board WHERE black = ? AND white = ?", blackUser.getName(),
                whiteUser.getName());

        return !findBoardByUser(blackUser, whiteUser).isPresent();
    }

    private ResultSet findBoardResultSet(User blackUser, User whiteUser) throws SQLException {
        return dbConnector.executeQuery("SELECT * FROM board WHERE black = ? AND white = ?", blackUser.getName(),
                whiteUser.getName());
    }
}
