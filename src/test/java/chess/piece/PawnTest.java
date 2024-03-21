package chess.piece;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.board.Position;
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
                () -> assertTrue(whitePawn.isAttackable(position, Position.of("c", 5))),
                () -> assertTrue(whitePawn.isAttackable(position, Position.of("e", 5))),
                () -> assertTrue(blackPawn.isAttackable(position, Position.of("c", 3))),
                () -> assertTrue(blackPawn.isAttackable(position, Position.of("e", 3)))
        );
    }
}
