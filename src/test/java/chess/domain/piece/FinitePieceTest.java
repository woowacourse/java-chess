package chess.domain.piece;

import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import chess.domain.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinitePieceTest extends AbstractTestFixture {

    public static class FinitePieceImplement extends FinitePiece {

        public FinitePieceImplement(boolean isWhite, Set<Move> moves) {
            super(isWhite, moves);
        }
    }

    @DisplayName("가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = createMove(UP, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        Piece piece = createPiece(move, move2);
        assertThat(piece.canMove(move)).isTrue();
    }

    @DisplayName("가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(move);
        assertThat(piece.canMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("가능하지 않은 수인지 판단한다")
    @Test
    void isInvalidMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(move);
        assertThat(piece.canMove(createMove(LEFT))).isFalse();
    }

    public Piece createPiece(Move... moves) {
        return new FinitePieceImplement(true, Set.of(moves));
    }
}
