package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PositionDAOTest {

    private final RepositoryUtil repositoryUtil = new RepositoryUtil();
    private UserDAO userDAO;
    private BoardDAO boardDAO;
    private PositionDAO positionDAO;

    @BeforeEach
    void setUp() throws SQLException {
        userDAO = new UserDAO(repositoryUtil);
        userDAO.addUser(UserDAOTest.TEST_USER1);
        userDAO.addUser(UserDAOTest.TEST_USER2);

        boardDAO = new BoardDAO(repositoryUtil);
        Board board = Board.createEmpty().placeInitialPieces();
        boardDAO.addBoard(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2, board);

        positionDAO = new PositionDAO(repositoryUtil);
    }

    @Test
    void crud() throws SQLException {
        Position position = Position.from("e5");
        Position modified = Position.from("e7");
        int boardId = boardDAO.findIdByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        positionDAO.deleteByBoardIdAndName(boardId, modified);

        positionDAO.addPosition(boardId, position);

        positionDAO.updateByBoardIdAndName(boardId, position, modified);

        assertThat(positionDAO.findPositionByBoardIdAndName(boardId, modified.getName()))
                .isEqualTo(modified);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        boardDAO.deleteByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        userDAO.deleteByName(UserDAOTest.TEST_USER2.getName());
        userDAO.deleteByName(UserDAOTest.TEST_USER1.getName());
    }
}