package chess.domain.position.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Camp;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlockingMoveTest {

    @Test
    @DisplayName("도착지점이 아닌 경우 기물을 통과할 수 없다.")
    void movableTest() {
        PieceMove blockingMove = new BlockingMove();
        boolean movableThroughPawn = blockingMove.isMovable(new Pawn(Camp.WHITE), false);

        assertThat(movableThroughPawn).isFalse();
    }

    @Test
    @DisplayName("도착지점인 경우 기물의 유무와 상관없이 움직일 수 있다.")
    void movableLastTest() {
        PieceMove blockingMove = new BlockingMove();
        boolean movableThroughPawn = blockingMove.isMovable(new Pawn(Camp.WHITE), true);

        assertThat(movableThroughPawn).isTrue();
    }
}
