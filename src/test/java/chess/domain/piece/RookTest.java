package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Direction;

public class RookTest {
    @Test
    @DisplayName("룩은 위쪽으로 2칸 이동 가능하다")
    void canMove_a1_a3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Direction(0, 2), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("룩은 오른쪽으로 1칸 위로 2칸 이동 불가능하다")
    void canMove_a1_b3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Direction(1, 2), new None(Color.NONE));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("룩은 오른쪽으로 1칸 이동 가능하다")
    void canMove_a1_b1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Direction(1, 0), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("룩은 왼쪽으로 2칸 이동 가능하다")
    void canMove_a3_a1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Direction(-2, 0), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("같은 편이 있는 위치로는 이동 불가능하다")
    void cantMove_sameTeamPosition() {
        Rook rook = new Rook(Color.BLACK);
        assertThatThrownBy(() -> rook.canMove(new Direction(2, 2), new Bishop(Color.BLACK)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.");
    }
}
