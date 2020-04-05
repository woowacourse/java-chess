package chess.service;

import java.sql.SQLException;

import chess.dao.BoardDAO;
import chess.dao.UserDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.player.User;
import chess.domain.result.ChessResult;

public class ChessService {

    private Board board;
    private BoardDAO boardDAO;

    public ChessService() {
        boardDAO = new BoardDAO();
    }

    public Board createEmpty() {
        board = BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);
        return board;
    }

    public Board findByUserName(User first, User second) throws SQLException {
        if (!boardDAO.findByUserName(first, second).isPresent()) {
            UserDAO userDAO = new UserDAO();
            userDAO.addUser(first);
            userDAO.addUser(second);
            boardDAO.addBoard(BoardFactory.createInitialBoard(first, second), first, second);
            board = BoardFactory.createInitialBoard(first, second);
            return board;
        }
        board = boardDAO.findByUserName(first, second).orElse(BoardFactory.createInitialBoard(first, second));
        return board;
    }

    public Board move(String source, String target) {
        board = board.move(source, target);
        return board;
    }

    public void save() throws SQLException {
        boardDAO.saveBoardByUserName(board, board.getFirstUser(), board.getSecondUser());
    }

    public ChessResult calculateResult() {
        return board.calculateResult();
    }

    public boolean checkGameNotFinished() {
        return board.isNotFinished();
    }

    public Board getBoard() {
        return board;
    }
}
