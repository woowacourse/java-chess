package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PassingMoveTest {

    @Test
    @DisplayName("통과할 수 있는 이동은 항상 이동할 수 없다.")
    void movableTest() {
        PieceMove passingMove = new PassingMove();
        assertAll(() -> {
            assertThat(passingMove.isMovable(new Pawn(Camp.WHITE), false)).isTrue();
            assertThat(passingMove.isMovable(new Pawn(Camp.WHITE), true)).isTrue();
            assertThat(passingMove.isMovable(new Empty(), false)).isTrue();
            assertThat(passingMove.isMovable(new Empty(), true)).isTrue();
        });
    }
}
