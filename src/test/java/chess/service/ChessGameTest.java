package chess.service;

import chess.domain.board.Board;
import chess.repository.BoardDAO;
import chess.repository.DBConnector;
import chess.repository.UserDAO;
import chess.repository.UserDAOTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    void saveAndLoad() throws SQLException {
        chessGame.saveUsers(UserDAOTest.TEST_USER1.getName(), UserDAOTest.TEST_USER2.getName());

        Board board = Board.createEmpty().placeInitialPieces();
        chessGame.saveBoard(board, UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);
        assertThat(chessGame.loadBoard(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2)).isEqualTo(board);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        DBConnector DBConnector = new DBConnector();

        new BoardDAO(DBConnector).deleteByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        UserDAO userDAO = new UserDAO(DBConnector);
        userDAO.deleteByName(UserDAOTest.TEST_USER2.getName());
        userDAO.deleteByName(UserDAOTest.TEST_USER1.getName());
    }
}