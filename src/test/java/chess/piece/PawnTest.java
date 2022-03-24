package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Square;

public class PawnTest {
    @Test
    @DisplayName("A1에 있는 폰을 A2로 이동 가능하다")
    void canMove_a1_a2() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Square("a1"), new Square("a2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 폰은 B3로 이동 불가능하다")
    void canMove_a1_b3() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Square("a1"), new Square("b3"));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("A1에 있는 폰은 A4로 이동 불가능하다")
    void canMove_a1_a4() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Square("a1"), new Square("a4"));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("폰의 첫 이동이면 A1에서 A3로 두 칸 이동 가능하다")
    void canMove_a1_a3() {
        Pawn pawn = new Pawn(Color.BLACK);
        Boolean canMove = pawn.canMove(new Square("a1"), new Square("a3"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("폰의 첫 이동이 아니면 A2에서 A4로 두 칸 이동 불가능하다")
    void canMove_a2_a4() {
        Pawn pawn = new Pawn(Color.BLACK);
        boolean canMove = pawn.canMove(new Square("a1"), new Square("a2"));
        canMove = pawn.canMove(new Square("a2"), new Square("a4"));

        assertThat(canMove).isFalse();
    }
}
