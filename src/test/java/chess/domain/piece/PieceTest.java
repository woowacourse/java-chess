package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import chess.domain.Move;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest extends AbstractTestFixture {

    public static class PieceImplement extends Piece {

        public PieceImplement(boolean isWhite, boolean isFinite, List<Move> moves) {
            super(isWhite, isFinite, moves);
        }
    }

    private Piece piece;

    @BeforeEach
    void setUpPiece() {
        piece = createPiece(true, true);
    }

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece otherPiece = createPiece(false, true);

        assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }

    @DisplayName("가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = createMove(UP, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        Piece piece = createPiece(true, true, move, move2);
        assertThat(piece.hasMove(move)).isTrue();
    }

    @DisplayName("가능한 수인지 판단한다(무한)")
    @Test
    void isValidMoveInfinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(true, false, move);
        assertThat(piece.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isTrue();
    }

    @DisplayName("가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(true, true, move);
        assertThat(piece.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("1사분면의 수으로 모든 사분면의 수을 만든다")
    @Test
    void flipMoves() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(true, true, move);
        assertThat(piece.hasMove(createMove(UP, RIGHT))).isTrue();
        assertThat(piece.hasMove(createMove(UP, LEFT))).isTrue();
        assertThat(piece.hasMove(createMove(DOWN, LEFT))).isTrue();
        assertThat(piece.hasMove(createMove(DOWN, RIGHT))).isTrue();
    }
}
