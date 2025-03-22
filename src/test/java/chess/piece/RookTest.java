package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩은 세로로 움직일 수 잇다.")
    @Test
    void Rook_canMove_Straight_Row() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.canMove(A1, A8);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 가로로 움직일 수 잇다.")
    @Test
    void Rook_canMove_Straight_Column() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.canMove(A1, H1);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 직선이 아니면 움직일 수 없다.")
    @Test
    void Rook_cannotMove_when_notStraight() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.canMove(A1, B2);

        // then
        assertThat(canMove).isFalse();
    }

}