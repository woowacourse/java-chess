package chess.domain.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.util.RenderingUtils;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    void initializeBoard() {
        Board initializedBoard = BoardFactory.initializeBoard();
        assertEquals(RenderingUtils.renderBoard(initializedBoard), validRenderedBoard());
    }

    String validRenderedBoard() {
        return "RNBQKBNR\n"
                + "PPPPPPPP\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "........\n"
                + "pppppppp\n"
                + "rnbqkbnr";
    }
}