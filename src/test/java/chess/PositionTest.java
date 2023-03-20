package chess;

import static chess.PositionFixtures.A6;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Direction;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void name() {
        final var canMove = A6.canMove(Direction.SOUTH);

        assertThat(canMove).isTrue();
    }
}
