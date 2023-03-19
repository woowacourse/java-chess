package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvalidMoveTest {

    @Test
    @DisplayName("비정상적인 이동은 항상 이동할 수 없다.")
    void movableTest() {
        PieceMove invalidMove = new InvalidMove();
        assertAll(() -> {
            assertThat(invalidMove.isMovable(new Pawn(Camp.WHITE), false)).isFalse();
            assertThat(invalidMove.isMovable(new Pawn(Camp.WHITE), true)).isFalse();
            assertThat(invalidMove.isMovable(new Empty(), false)).isFalse();
            assertThat(invalidMove.isMovable(new Empty(), true)).isFalse();
        });
    }
}
