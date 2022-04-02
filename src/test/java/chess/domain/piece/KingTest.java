package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Movement;

public class KingTest {
    @Test
    @DisplayName("킹을 오른쪽으로 한칸 움직일 수 있다")
    void canMove_a1_a2() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Movement(0, 1), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("킹을 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_b1() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Movement(1, 0), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("킹을 오른쪽으로 한칸, 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_b2() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Movement(1, 1), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("킹을 오른쪽으로 두칸, 위쪽으로 두칸 이동 불가능하다")
    void canMove_a1_c3() {
        King king = new King(Color.BLACK);
        Boolean canMove = king.canMove(new Movement(2, 2), new None(Color.NONE));

        assertThat(canMove).isFalse();
    }
}
