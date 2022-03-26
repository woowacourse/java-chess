package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Direction;

public class KnightTest {
    @Test
    @DisplayName("나이트를 오른쪽으로 한칸, 위쪽으로 두칸 이동 가능하다")
    void canMove_a1_b3() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Direction(1, 2), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("나이트를 오른쪽으로 두칸, 위쪽으로 한칸 이동 가능하다")
    void canMove_a1_c2() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Direction(2, 1), new None(Color.NONE));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽으로 2칸 위로 2칸 이동 불가능하다")
    void canMove_a1_c3() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Direction(2, 2), new None(Color.NONE));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("같은 편이 있는 위치로는 이동 불가능하다")
    void cantMove_sameTeamPosition() {
        Knight knight = new Knight(Color.BLACK);
        assertThatThrownBy(() -> knight.canMove(new Direction(2, 2), new Bishop(Color.BLACK)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.");
    }
}
