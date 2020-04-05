package chess.repository;

import chess.domain.board.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDAOTest {

    private ChessDAO chessDAO;

    @BeforeEach
    void setUp() {
        chessDAO = new ChessDAO();
    }

    @Test
    void saveAndLoad() throws SQLException {
        chessDAO.saveUsers(UserDAOTest.TEST_USER1.getName(), UserDAOTest.TEST_USER2.getName());

        Board board = Board.createEmpty().placeInitialPieces();
        chessDAO.saveBoard(board, UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);
        assertThat(chessDAO.loadBoard(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2)).isEqualTo(board);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        RepositoryUtil repositoryUtil = new RepositoryUtil();

        new BoardDAO(repositoryUtil).deleteByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        UserDAO userDAO = new UserDAO(repositoryUtil);
        userDAO.deleteByName(UserDAOTest.TEST_USER2.getName());
        userDAO.deleteByName(UserDAOTest.TEST_USER1.getName());
    }
}