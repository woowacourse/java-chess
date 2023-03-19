package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnCatchMoveTest {

    @Test
    @DisplayName("도착지점에 기물이 존재하는 경우에만 이동할 수 있다.")
    void movableTest() {
        PieceMove pawnCatchMove = new PawnCatchMove();
        assertAll(() -> {
            assertThat(pawnCatchMove.isMovable(new Pawn(Camp.WHITE), true)).isTrue();
            assertThat(pawnCatchMove.isMovable(new Empty(), true)).isFalse();
        });
    }
}
