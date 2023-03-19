package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnForwardMoveTest {

    @Test
    @DisplayName("이동하는 경로에 기물이 존재하는 경우 이동할 수 없다.")
    void movableThroughPieceTest() {
        PieceMove pawnForwardMove = new PawnForwardMove();
        assertAll(() -> {
            assertThat(pawnForwardMove.isMovable(new Pawn(Camp.WHITE), false)).isFalse();
            assertThat(pawnForwardMove.isMovable(new Empty(), false)).isTrue();
        });
    }
}
