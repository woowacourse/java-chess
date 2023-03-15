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

public class PieceTest extends AbstractTestFixture {

    public static class PieceImplement extends Piece {

        public PieceImplement(boolean isWhite, Set<Move> moves) {
            super(isWhite, moves);
        }
    }

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece piece = createPiece(true);
        Piece otherPiece = createPiece(false);

        assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }

    @DisplayName("가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = createMove(UP, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        Piece piece = createPiece(true, move, move2);
        assertThat(piece.canMove(move)).isTrue();
    }

    @DisplayName("가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(true, move);
        assertThat(piece.canMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("가능하지 않은 수인지 판단한다")
    @Test
    void isInvalidMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(true, move);
        assertThat(piece.canMove(createMove(LEFT))).isFalse();
    }

    @DisplayName("기본적으로 Touch 시 상태가 변하지 않는다")
    @Test
    void touch_nothingHappens() {
        Piece piece = createPiece(true);

        assertThat(piece.touch()).isSameAs(piece);
    }

    private Piece createPiece(boolean isWhite, Move... moves) {
        return new PieceTest.PieceImplement(isWhite, Set.of(moves));
    }
}
