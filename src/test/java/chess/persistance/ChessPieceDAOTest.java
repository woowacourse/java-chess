package chess.persistance;

import chess.model.AbstractChessPiece;
import chess.model.pieces.BlackPawn;
import chess.model.coordinate.Point;
import chess.model.pieces.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceDAOTest {
    private ChessPieceDAO chessPieceDAO;
    private GameDAO gameDAO;

    @BeforeEach
    void setUp() {
        this.chessPieceDAO = ChessPieceDAO.getInstance();
        this.gameDAO = GameDAO.getInstance();
    }

    @Test
    void addChessPieceTest() {
        Point point = new Point(1, 1);
        AbstractChessPiece chessPiece = new BlackPawn();
        gameDAO.addGame("New Game");
        int gameId = gameDAO.getGameId("New Game");

        int numOfChessPiecesBeforeAdd = chessPieceDAO.countPieces(gameId);
        chessPieceDAO.addPiece(point, chessPiece, gameId);
        int numofChessPiecesAfterAdd = chessPieceDAO.countPieces(gameId);

        assertThat(numofChessPiecesAfterAdd).isEqualTo(numOfChessPiecesBeforeAdd + 1);
        gameDAO.removeGame(gameId);  // ON_DELETE CASCADE
    }

    @Test
    void getAllPiece_test() {
        gameDAO.addGame("New Game");
        int gameId = gameDAO.getGameId("New Game");
        chessPieceDAO.addPiece(new Point(1, 2), new BlackPawn(), gameId);
        chessPieceDAO.addPiece(new Point(1, 7), new WhitePawn(), gameId);

        Map<Point, AbstractChessPiece> actual = chessPieceDAO.getAll(gameId);
        Map<Point, AbstractChessPiece> expected = new HashMap<>();
        expected.put(new Point(1, 2), new BlackPawn());
        expected.put(new Point(1, 7), new WhitePawn());

        assertThat(actual).isEqualTo(expected);
        gameDAO.removeGame(gameId);  // ON_DELETE CASCADE
    }

    @Test
    void remove_test() {
        gameDAO.addGame("New Game");
        int gameId = gameDAO.getGameId("New Game");
        int before = chessPieceDAO.countPieces(gameId);
        chessPieceDAO.addPiece(new Point(1, 2), new BlackPawn(), gameId);
        chessPieceDAO.addPiece(new Point(1, 7), new WhitePawn(), gameId);
        chessPieceDAO.removePiece(new Point(1, 2), gameId);
        int after = chessPieceDAO.countPieces(gameId);
        assertThat(before).isEqualTo(after - 1);
        gameDAO.removeGame(gameId);  // ON_DELETE CASCADE
    }

    @Test
    void update_test() {
        gameDAO.addGame("New Game");
        int gameId = gameDAO.getGameId("New Game");
        chessPieceDAO.addPiece(new Point(1, 2), new BlackPawn(), gameId);
        chessPieceDAO.updatePiece(new Point(1, 2), new Point(1, 4), gameId);
        Map<Point, AbstractChessPiece> actual = chessPieceDAO.getAll(gameId);
        assertThat(actual.containsKey(new Point(1, 4))).isTrue();
        gameDAO.removeGame(gameId);  // ON_DELETE CASCADE
    }
}
