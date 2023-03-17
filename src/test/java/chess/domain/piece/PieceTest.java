package chess.domain.piece;

import static chess.domain.board.MoveType.ATTACK;
import static chess.domain.board.MoveType.MOVE;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    public static class PieceImplement extends Piece {

        public PieceImplement(Color color, Set<Move> moves) {
            super(color, moves);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("공격 가능한 상대인지 판단한다")
    @Test
    void isRightTarget() {
        Piece piece = createPiece(WHITE);
        Piece otherPiece = createPiece(BLACK);

        assertThat(piece.isRightTarget(otherPiece)).isTrue();
    }

    @DisplayName("공격 불가능한 상대인지 판단한다")
    @Test
    void isNotRightTarget() {
        Piece piece = createPiece(WHITE);
        Piece otherPiece = createPiece(WHITE);

        assertThat(piece.isRightTarget(otherPiece)).isFalse();
    }

    @DisplayName("이동 가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = new Move(UP, UP, RIGHT);
        Move move2 = new Move(RIGHT, RIGHT, UP);

        Piece piece = createPiece(WHITE, move, move2);
        assertThat(piece.isValidMove(move, MOVE)).isTrue();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = new Move(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.isValidMove(new Move(UP, RIGHT, UP, RIGHT), MOVE)).isFalse();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다")
    @Test
    void isInvalidMove() {
        Move move = new Move(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.isValidMove(new Move(LEFT), MOVE)).isFalse();
    }

    @DisplayName("기본적으로 이동 가능한 수면, 공격 가능한 수이다")
    @Test
    void hasMove_then_hasAttackMove() {
        Move move = new Move(UP, RIGHT);

        Piece piece = createPiece(WHITE, move);
        assertThat(piece.isValidMove(move, ATTACK)).isTrue();
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
