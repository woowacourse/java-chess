package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("말은 수평한칸, 수직두칸 이동할 수 있다.")
    @Test
    void Horse_canMoveTest() {
        // given
        Piece knight = new Knight();

        // when
        boolean canMove = knight.canMove(B1, C3);
        boolean canMove2 = knight.canMove(B1, A3);

        // then
        assertThat(canMove).isTrue();
        assertThat(canMove2).isTrue();
    }

    @DisplayName("말이 이동불가능한 경로로는 갈 수 없다.")
    @Test
    void Horse_canMove_False() {
        // given
        Piece knight = new Knight();

        // when
        boolean canMove = knight.canMove(B1, C2);

        // then
        assertThat(canMove).isFalse();
    }

}