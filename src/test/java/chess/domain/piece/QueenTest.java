package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Square;

public class QueenTest {
    @Test
    @DisplayName("A1에 있는 퀸을 A2로 이동 가능하다")
    void canMove_a1_a2() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Square("a1"), new Square("a2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 퀸을 B1로 이동 가능하다")
    void canMove_a1_b1() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Square("a1"), new Square("b1"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있을 퀸을 B2로 이동 가능하다")
    void canMove_a1_b2() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Square("a1"), new Square("b2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 퀸을 C3로 이동 가능하다")
    void canMove_a1_c3() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Square("a1"), new Square("c3"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 퀸을 C4로 이동 불가능하다")
    void canMove_a1_c4() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Square("a1"), new Square("c4"));

        assertThat(canMove).isFalse();
    }
}
