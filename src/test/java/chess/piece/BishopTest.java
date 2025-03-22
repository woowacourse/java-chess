package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍은 대각선으로 갈 수 있다.")
    @Test
    void Bishop_canMove_diagonal() {
        // given
        Bishop bishop = new Bishop();

        // when
        boolean canMove = bishop.canMove(A1, B2);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 직선으로 갈 수 없다.")
    @Test
    void Bishop_cannotMove_Straight() {
        // given
        Bishop bishop = new Bishop();

        // when
        boolean canMove = bishop.canMove(A1, A2);

        // then
        assertThat(canMove).isFalse();
    }
}