package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.move.PassingMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PassingMoveTest {

    @Test
    @DisplayName("통과할 수 있는 이동은 항상 이동할 수 없다.")
    void movableTest() {
        PieceMove passingMove = new PassingMove();
        boolean isExistPiece = true;
        boolean isEmpty = false;

        assertAll(() -> {
            assertThat(passingMove.isMovable(isExistPiece, false)).isTrue();
            assertThat(passingMove.isMovable(isExistPiece, true)).isTrue();
            assertThat(passingMove.isMovable(isEmpty, false)).isTrue();
            assertThat(passingMove.isMovable(isEmpty, true)).isTrue();
        });
    }
}
