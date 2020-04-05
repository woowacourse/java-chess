package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class BoardDAOTest {

    private final RepositoryUtil repositoryUtil = new RepositoryUtil();
    private UserDAO userDAO;
    private BoardDAO boardDAO;

    @BeforeEach
    void setUp() throws SQLException {
        boardDAO = new BoardDAO(repositoryUtil);

        userDAO = new UserDAO(repositoryUtil);
        userDAO.addUser(UserDAOTest.TEST_USER1);
        userDAO.addUser(UserDAOTest.TEST_USER2);
    }

    @Test
    void crud() throws SQLException {
        Board board = Board.createEmpty().placeInitialPieces();
        boardDAO.addBoard(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2, board);

        Board updatedBoard = board.move(Position.from("e2"), Position.from("e4"));
        boardDAO.updateByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2, updatedBoard);

        assertThat(boardDAO.findStatusByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2).getTurn()).isEqualTo(1);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        boardDAO.deleteByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        userDAO.deleteByName(UserDAOTest.TEST_USER2.getName());
        userDAO.deleteByName(UserDAOTest.TEST_USER1.getName());
    }
}