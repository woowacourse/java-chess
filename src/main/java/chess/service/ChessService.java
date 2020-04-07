package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.BoardDAO;
import chess.dao.UserDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.player.User;
import chess.domain.result.ChessResult;

public class ChessService {

    private BoardDAO boardDAO;
    private Map<User, Board> boards;

    public ChessService() {
        boardDAO = new BoardDAO();
        boards = new HashMap<>();
    }

    public Board createEmpty() {
        return BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);
    }

    public Board findByUserName(User first, User second) throws SQLException {
        Board board;
        if (!boardDAO.findBoardByUser(first, second).isPresent()) {
            UserDAO userDAO = new UserDAO();
            userDAO.addUser(first);
            userDAO.addUser(second);
            boardDAO.addBoard(BoardFactory.createInitialBoard(first, second), first, second);
            board = BoardFactory.createInitialBoard(first, second);
            boards.put(first, board);
            return board;
        }
        board = boardDAO.findBoardByUser(first, second).orElse(BoardFactory.createInitialBoard(first, second));
        boards.put(first, board);
        return board;
    }

    public Board move(User first, String source, String target) {
        Board board = boards.get(first).move(source, target);
        boards.put(first, board);
        return board;
    }

    public void save(User first, User second) throws SQLException {
        boardDAO.saveBoardByUserName(boards.get(first), first, second);
    }

    public void delete(User first, User second) throws SQLException {
        boardDAO.deleteBoardByUser(first, second);
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUserByUserName(first.getName());
        userDAO.deleteUserByUserName(second.getName());
        boards.remove(first);
    }

    public ChessResult calculateResult(User first) {
        return boards.get(first).calculateResult();
    }

    public boolean checkGameNotFinished(User first) {
        return boards.get(first).isNotFinished();
    }

    public Board getBoard(User first) {
        return boards.get(first);
    }
}
