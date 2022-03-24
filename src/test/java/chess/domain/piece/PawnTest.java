package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Direction;
import chess.domain.position.Square;

public class PawnTest {
    @Test
    @DisplayName("폰을 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_a2() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Direction(0, 1));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("폰을 오른쪽으로 한칸, 위쪽으로 두칸 이동 불가능하다")
    void canMove_a1_b3() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Direction(1, 2));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("폰을 위쪽으로 세칸 이동 불가능하다")
    void canMove_a1_a4() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Direction(0, 3));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("폰의 첫 이동이면 위쪽으로 두칸 이동 가능하다")
    void canMove_a1_a3() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Direction(0, 2));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("폰의 첫 이동이 아니면 위로 두 칸 이동 불가능하다")
    void canMove_a2_a4() {
        Pawn pawn = new Pawn(Color.BLACK);
        boolean canMove = pawn.canMove(new Direction(0,2));
        canMove = pawn.canMove(new Direction(0,2));

        assertThat(canMove).isFalse();
    }
}
