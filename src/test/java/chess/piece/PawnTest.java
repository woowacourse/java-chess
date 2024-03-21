package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 대각선 전진 방향으로 공격할 수 있다.")
    void pawnAttackableTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        Position position = Position.of("d", 4);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isAttackable(position, Position.of("c", 5))).isTrue(),
                () -> assertThat(whitePawn.isAttackable(position, Position.of("e", 5))).isTrue(),
                () -> assertThat(blackPawn.isAttackable(position, Position.of("c", 3))).isTrue(),
                () -> assertThat(blackPawn.isAttackable(position, Position.of("e", 3))).isTrue()
        );
    }
}
