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
        return "\n  a b c d e f g h\n"
            + "8 R N B Q K B N R 8\n"
            + "7 P P P P P P P P 7\n"
            + "6 . . . . . . . . 6\n"
            + "5 . . . . . . . . 5\n"
            + "4 . . . . . . . . 4\n"
            + "3 . . . . . . . . 3\n"
            + "2 p p p p p p p p 2\n"
            + "1 r n b q k b n r 1\n"
            + "  a b c d e f g h\n";
    }
}