package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Direction;
import chess.domain.position.Square;

public class QueenTest {
    @Test
    @DisplayName("퀸을 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_a2() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Direction(0, 1));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("퀸을 오른쪽으로 한칸 이동 가능하다")
    void canMove_a1_b1() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Direction(1, 0));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("퀸을 오른쪽으로 한칸, 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_b2() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Direction(1, 1));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("퀸을 오른쪽으로 두칸, 위쪽으로 두칸 이동 가능하다")
    void canMove_a1_c3() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Direction(2,2));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("퀸은 오른쪽으로 2칸 위로 3칸 이동 불가능하다다")
    void canMove_a1_c4() {
        Queen queen = new Queen(Color.BLACK);
        Boolean canMove = queen.canMove(new Direction(2,3));

        assertThat(canMove).isFalse();
    }
}
