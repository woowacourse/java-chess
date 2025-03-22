package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸은 대각선으로 이동 가능")
    @Test
    void Queen_canMove_diagonal() {
        // given
        Queen queen = new Queen();

        // when
        boolean canMove = queen.canMove(A1, C3);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선으로 이동 가능")
    @Test
    void Queen_canMove_Straight() {
        // given
        Queen queen = new Queen();

        // when
        boolean canMove = queen.canMove(A1, A3);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선이나 대각선이 아닌 곳으로는 이동 불가능")
    @Test
    void Queen_cannotMove() {
        // given
        Queen queen = new Queen();

        // when
        boolean canMove = queen.canMove(A1, B3);

        // then
        assertThat(canMove).isFalse();
    }

}