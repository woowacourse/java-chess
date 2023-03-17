package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuadrantPieceTest {

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
        Piece piece = new QuadrantPieceImplement(BLACK, Set.of(new Move(UP, RIGHT)));

        assertThat(piece.hasMove(new Move(UP, RIGHT))).isTrue();
        assertThat(piece.hasMove(new Move(UP, LEFT))).isTrue();
        assertThat(piece.hasMove(new Move(DOWN, LEFT))).isTrue();
        assertThat(piece.hasMove(new Move(DOWN, RIGHT))).isTrue();
    }
}
