package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Movement;

public class RookTest {
    @Test
    @DisplayName("룩은 위쪽으로 2칸 이동 가능하다")
    void canMove_a1_a3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Movement(0, 2), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("룩은 오른쪽으로 1칸 위로 2칸 이동 불가능하다")
    void canMove_a1_b3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Movement(1, 2), new None(Color.NONE));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("룩은 오른쪽으로 1칸 이동 가능하다")
    void canMove_a1_b1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Movement(1, 0), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("룩은 왼쪽으로 2칸 이동 가능하다")
    void canMove_a3_a1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Movement(-2, 0), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }
}
