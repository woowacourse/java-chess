package chess.domain.pieces;

import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmptyTest {

    @Test
    void 빈_공간_움직임_에러() {
        assertThrows(IllegalArgumentException.class, () -> new Empty().move(new Point(1, 1), new Point(1, 2)));
    }
}