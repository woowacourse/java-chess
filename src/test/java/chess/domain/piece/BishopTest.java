package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Movement;

public class BishopTest {
    @Test
    @DisplayName("비숍을 오른쪽으로 2칸 위쪽으로 2칸 이동 가능하다")
    void canMove_a1_c3() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Movement(2, 2), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("비숍을 왼쪽으로 3칸 아래쪽으로 3칸 이동 가능하다")
    void canMove_d1_a4() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Movement(-3, -3), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("비숍을 위쪽으로 칸 이동 불가능하다")
    void canMove_a1_a5() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Movement(0, 4), new None(Color.NONE));

        assertThat(canMove).isFalse();
    }
}
