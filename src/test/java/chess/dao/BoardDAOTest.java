package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.player.User;
import chess.view.OutputView;

class BoardDAOTest {

    private BoardDAO boardDao;
    private User firstUser;
    private User secondUser;

    @BeforeEach
    public void setup() {
        boardDao = new BoardDAO();
        firstUser = new User("first");
        secondUser = new User("second");
    }

    @Test
    public void addBoard() throws Exception {
        Board board = BoardFactory.createInitialBoard();

        boardDao.addBoard(board, firstUser, secondUser);
    }

    @Test
    public void findByUserName() throws Exception {
        Board board = boardDao.findByUserName(firstUser, secondUser);
        assertEquals(BoardFactory.createInitialBoard(), board);
    }

}