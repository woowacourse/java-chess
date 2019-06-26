package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Position;
import chess.exception.InvalidTurnException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.board.BoardInputForTest.DEFAULT_BOARD;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    private Board board = BoardGenerator.createBoard(DEFAULT_BOARD);
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(board);
    }

    @Test
    public void 현재_차례가_아닌_보드를_움직이려고_할때_예외처리() {
        assertThrows(InvalidTurnException.class, () -> {
            game.selectSourcePiece(Position.of(Position.makeKey(6, 0)));
        });
    }

    @AfterEach
    public void tearDown() {
        game = null;
    }
}