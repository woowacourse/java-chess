package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvalidMoveTest {

    @Test
    @DisplayName("비정상적인 이동은 항상 이동할 수 없다.")
    void movableTest() {
        PieceMove invalidMove = new InvalidMove();
        boolean isExistPiece = true;
        boolean isEmpty = false;

        assertAll(() -> {
            assertThat(invalidMove.isMovable(isExistPiece, false)).isFalse();
            assertThat(invalidMove.isMovable(isExistPiece, true)).isFalse();
            assertThat(invalidMove.isMovable(isEmpty, false)).isFalse();
            assertThat(invalidMove.isMovable(isEmpty, true)).isFalse();
        });
    }
}
