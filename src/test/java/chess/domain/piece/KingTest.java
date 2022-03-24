package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("올바른 이동")
    @Test
    void move() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a2");

        King king = new King();

        // then
        assertThat(king.movable(to, from)).isTrue();
    }

    @DisplayName("올바르지 않은 이동")
    @Test
    void move_x() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a5");

        King king = new King();

        // then
        assertThat(king.movable(to, from)).isFalse();
    }
}