package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class GamePieceDAOTest {

    private final RepositoryUtil repositoryUtil = new RepositoryUtil();
    private UserDAO userDAO;
    private BoardDAO boardDAO;
    private PositionDAO positionDAO;
    private int positionId;
    private GamePieceDAO gamePieceDAO;

    @BeforeEach
    void setUp() throws SQLException {
        userDAO = new UserDAO(repositoryUtil);
        userDAO.addUser(UserDAOTest.TEST_USER1);
        userDAO.addUser(UserDAOTest.TEST_USER2);

        boardDAO = new BoardDAO(repositoryUtil);
        Board board = Board.createEmpty().placeInitialPieces();
        boardDAO.addBoard(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2, board);
        int boardId = boardDAO.findIdByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        positionDAO = new PositionDAO(repositoryUtil);
        Position position = Position.from("e5");
        positionDAO.addPosition(boardId, position);
        positionId = positionDAO.findIdByBoardIdAndName(boardId, "e5");

        gamePieceDAO = new GamePieceDAO(repositoryUtil);
    }

    @Test
    void crud() throws SQLException {
        GamePiece gamePiece = ChessPiece.BLACK_PAWN.getGamePiece();
        GamePiece modified = ChessPiece.WHITE_PAWN.getGamePiece();

        gamePieceDAO.deleteByPositionId(positionId);

        gamePieceDAO.addGamePiece(positionId, gamePiece);

        gamePieceDAO.updateByPositionId(positionId, modified);

        assertThat(gamePieceDAO.findGamePieceByPositionId(positionId))
                .isEqualTo(modified);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        boardDAO.deleteByUsers(UserDAOTest.TEST_USER1, UserDAOTest.TEST_USER2);

        userDAO.deleteByName(UserDAOTest.TEST_USER2.getName());
        userDAO.deleteByName(UserDAOTest.TEST_USER1.getName());
    }
}