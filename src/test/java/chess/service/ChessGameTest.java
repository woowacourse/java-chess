package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
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
        chessGame.start(UserDAOTest.TEST_USER1.getName(), UserDAOTest.TEST_USER2.getName());

        Board board = chessGame.move(UserDAOTest.TEST_USER1.getName(), UserDAOTest.TEST_USER2.getName(),
                "e2", "e3");

        Board expected = Board.createEmpty().placeInitialPieces();
        expected = expected.move(Position.from("e2"), Position.from("e3"));

        assertThat(board).isEqualTo(expected);
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