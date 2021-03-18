package chess.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

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