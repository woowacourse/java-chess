package chess.domain.board;

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
        return "\nabcdefgh\n"
                + "RNBQKBNR 8\n"
                + "PPPPPPPP 7\n"
                + "........ 6\n"
                + "........ 5\n"
                + "........ 4\n"
                + "........ 3\n"
                + "pppppppp 2\n"
                + "rnbqkbnr 1"
                + "\nabcdefgh\n";
    }
}