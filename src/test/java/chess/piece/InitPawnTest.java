package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitPawnTest {

    @Test
    @DisplayName("최초 폰은 최대 2만큼 전진할 수 있다.")
    void initPawnMoveTest() {
        // given
        InitPawn whitePawn = new InitPawn(Color.WHITE);
        InitPawn blackPawn = new InitPawn(Color.BLACK);
        UnitMovement whiteDirection = UnitMovement.differencesOf(0, 1);
        UnitMovement blackDirection = UnitMovement.differencesOf(0, -1);
        // when
        boolean isWhiteMovable = whitePawn.isMovable(whiteDirection, 2);
        boolean isBlackMovable = blackPawn.isMovable(blackDirection, 2);
        // then
        assertAll(
                () -> assertThat(isWhiteMovable).isTrue(),
                () -> assertThat(isBlackMovable).isTrue()
        );
    }
}
