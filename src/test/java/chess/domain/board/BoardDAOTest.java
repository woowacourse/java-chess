package chess.domain.board;

import chess.domain.player.User;
import chess.domain.player.UserDAOTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDAOTest {

    private BoardDAO boardDAO;

    @BeforeEach
    public void setup() throws SQLException {
        boardDAO = new BoardDAO();
        deleteBoard(boardDAO);
    }

    @Test
    void crud() throws SQLException {
        UserDAOTest.addTwoTestUser();
        User user1 = new User(UserDAOTest.userName1);
        User user2 = new User(UserDAOTest.userName2);
        Board board = Board.createEmpty().placeInitialPieces();
        boardDAO.saveBoard(board, user1, user2);
        board.move(Position.from("e2"), Position.from("e3"));
        boardDAO.updateBoard(board, user1, user2);

        assertThat(boardDAO.findByUsers(user1, user2)).isEqualTo(board);
    }

    public static void deleteBoard(BoardDAO boardDAO) throws SQLException {
        boardDAO.deleteByUserNames(UserDAOTest.userName1, UserDAOTest.userName2);
        UserDAOTest.deleteTwoTestUser();
    }

    public static int addBoard(BoardDAO boardDAO) throws SQLException {
        UserDAOTest.addTwoTestUser();
        Board board = Board.createEmpty().placeInitialPieces();

        return boardDAO.addBoard(new User(UserDAOTest.userName1), new User(UserDAOTest.userName2), board);
    }
}