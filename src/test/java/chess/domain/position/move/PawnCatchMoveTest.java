package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.move.PawnCatchMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnCatchMoveTest {

    @Test
    @DisplayName("도착지점에 기물이 존재하는 경우에만 이동할 수 있다.")
    void movableTest() {
        PieceMove pawnCatchMove = new PawnCatchMove();
        boolean isExistPiece = true;
        boolean isEmpty = false;

        assertAll(() -> {
            assertThat(pawnCatchMove.isMovable(isExistPiece, true)).isTrue();
            assertThat(pawnCatchMove.isMovable(isEmpty, true)).isFalse();
        });
    }
}
