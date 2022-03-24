package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.state.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @Test
    @DisplayName("White 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void whitePawnNoMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(-1, -1))).isFalse();
    }

    @Test
    @DisplayName("Black 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void blackPawnNoMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovableDirection(Direction.of(1, 1))).isFalse();
    }

    @Test
    @DisplayName("White 폰에게 허용된 이동 명령을 했을 경우 true 반환 ")
    void whitePawnMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(0, 1))).isTrue();
    }
}