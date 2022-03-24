package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Square;

public class KingTest {
    @Test
    @DisplayName("A1에 있는 킹을 A2로 이동 가능하다")
    void canMove_a1_a2() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Square("a1"), new Square("a2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 킹을 B1로 이동 가능하다")
    void canMove_a1_b1() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Square("a1"), new Square("b1"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 킹을 B2로 이동 가능하다")
    void canMove_a1_b2() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Square("a1"), new Square("b2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 킹을 C3로 이동 가능하다")
    void canMove_a1_c3() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Square("a1"), new Square("c3"));

        assertThat(canMove).isFalse();
    }

}
