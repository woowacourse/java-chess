package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    Queen queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(C3, Team.BLACK);
    }

    @Test
    void moveTo() {
        queen.moveTo(C4);
        assertThat(queen.getPosition()).isEqualTo(C4);
    }

    @Test
    void canNotMoveTo_Return_True() {
        Piece target = new Empty(D5);
        assertThat(queen.canNotMoveTo(target)).isTrue();
    }

    @Test
    void canNotMoveTo_Return_False() {
        Piece target = new Empty(C4);
        assertThat(queen.canNotMoveTo(target)).isFalse();
    }
}