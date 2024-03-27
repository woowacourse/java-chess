package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovedPawnTest {

    @Test
    @DisplayName("폰은 최대 한 칸만 전진할 수 있다.")
    void pawnMoveTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        UnitMovement whiteDirection = UnitMovement.differencesOf(0, 1);
        UnitMovement blackDirection = UnitMovement.differencesOf(0, -1);
        // when
        boolean isWhiteMovable = whitePawn.isMovable(whiteDirection, 2);
        boolean isBlackMovable = blackPawn.isMovable(blackDirection, 2);
        // then
        assertAll(
                () -> assertThat(isWhiteMovable).isFalse(),
                () -> assertThat(isBlackMovable).isFalse()
        );
    }

    @Test
    @DisplayName("움직인 폰은 두 칸 이상 전진할 수 없다.")
    void pawnMaxUnitTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        UnitMovement whiteDirection = UnitMovement.differencesOf(0, 1);
        UnitMovement blackDirection = UnitMovement.differencesOf(0, -1);
        // when
        boolean isWhiteMovable = whitePawn.isMovable(whiteDirection, 2);
        boolean isBlackMovable = blackPawn.isMovable(blackDirection, 2);
        // then
        assertAll(
                () -> assertThat(isWhiteMovable).isFalse(),
                () -> assertThat(isBlackMovable).isFalse()
        );
    }
}
