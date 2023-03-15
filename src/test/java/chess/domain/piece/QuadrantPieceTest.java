package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuadrantPieceTest extends AbstractTestFixture {

    @DisplayName("1사분면의 수으로 모든 사분면의 수을 만든다")
    @Test
    void copyMoves() {
        Set<Move> copiedMoves = QuadrantPiece.copyMoves(Set.of(createMove(UP, RIGHT)));

        assertThat(copiedMoves)
                .containsExactlyInAnyOrder(
                        createMove(UP, RIGHT),
                        createMove(UP, LEFT),
                        createMove(DOWN, LEFT),
                        createMove(DOWN, RIGHT));
    }
}
