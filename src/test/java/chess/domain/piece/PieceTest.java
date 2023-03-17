package chess.domain.piece;

import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest extends AbstractTestFixture {

    public static class PieceImplement extends Piece {

        public PieceImplement(Color color, Set<Move> moves) {
            super(color, moves);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece piece = createPiece(WHITE);
        Piece otherPiece = createPiece(BLACK);

        assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }

    @DisplayName("이동 가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = createMove(UP, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        Piece piece = createPiece(WHITE, move, move2);
        assertThat(piece.hasMove(move)).isTrue();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다")
    @Test
    void isInvalidMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.hasMove(createMove(LEFT))).isFalse();
    }

    @DisplayName("기본적으로 이동 가능한 수면, 공격 가능한 수이다")
    @Test
    void hasMove_then_hasAttackMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.hasAttackMove(move)).isTrue();
    }

    @DisplayName("기본적으로 Touch 시 상태가 변하지 않는다")
    @Test
    void touch_nothingHappens() {
        Piece piece = createPiece(WHITE);

        assertThat(piece.touch()).isSameAs(piece);
    }

    private Piece createPiece(Color color, Move... moves) {
        return new PieceTest.PieceImplement(color, Set.of(moves));
    }
}
