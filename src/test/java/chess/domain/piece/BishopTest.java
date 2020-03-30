package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(C3, Team.BLACK);
    }

    @Test
    void moveTo() {
        bishop.moveTo(H8);
        assertThat(bishop.getPosition()).isEqualTo(H8);
    }

    @Test
    void canNotMoveTo_Return_True() {
        Piece target = new Empty(C4);
        assertThat(bishop.canNotMoveTo(target)).isTrue();
    }

    @Test
    void canNotMoveTo_Return_False() {
        Piece target = new Empty(B4);
        assertThat(bishop.canNotMoveTo(target)).isFalse();
    }
}