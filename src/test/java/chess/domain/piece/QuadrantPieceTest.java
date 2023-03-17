package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuadrantPieceTest extends AbstractTestFixture {

    static class QuadrantPieceImplement extends QuadrantPiece {

        public QuadrantPieceImplement(Color color, Set<Move> moves) {
            super(color, moves);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("1사분면의 수으로 모든 사분면의 수을 만든다")
    @Test
    void copyMoves() {
        Piece piece = new QuadrantPieceImplement(BLACK, Set.of(createMove(UP, RIGHT)));

        assertThat(piece.hasMove(createMove(UP, RIGHT))).isTrue();
        assertThat(piece.hasMove(createMove(UP, LEFT))).isTrue();
        assertThat(piece.hasMove(createMove(DOWN, LEFT))).isTrue();
        assertThat(piece.hasMove(createMove(DOWN, RIGHT))).isTrue();
    }
}
