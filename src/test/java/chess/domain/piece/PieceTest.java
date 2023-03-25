package chess.domain.piece;

import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;
import chess.domain.move.Move;

public class PieceTest extends AbstractTestFixture {

    public static class PieceImplement extends Piece {

        public PieceImplement(Team team, Set<Move> moves) {
            super(team, moves);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("팀을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece piece = createPiece(Team.WHITE);
        Piece otherPiece = createPiece(Team.BLACK);

        assertThat(piece.isSameTeamWith(otherPiece)).isFalse();
    }

    @DisplayName("이동 가능한 수인지 판단한다(유한)")
    @Test
    void isValidMoveFinite() {
        Move move = createMove(UP, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        Piece piece = createPiece(Team.WHITE, move, move2);
        assertThat(piece.hasMove(move)).isTrue();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다(기물:유한, 수:무한)")
    @Test
    void isInvalidMoveFinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(Team.WHITE, move);
        assertThat(piece.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("이동 가능하지 않은 수인지 판단한다")
    @Test
    void isInvalidMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(Team.WHITE, move);
        assertThat(piece.hasMove(createMove(LEFT))).isFalse();
    }

    @DisplayName("기본적으로 이동 가능한 수면, 공격 가능한 수이다")
    @Test
    void hasMove_then_hasAttackMove() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(Team.WHITE, move);
        assertThat(piece.hasAttackMove(move)).isTrue();
    }

    @DisplayName("기본적으로 Touch 시 상태가 변하지 않는다")
    @Test
    void touch_nothingHappens() {
        Piece piece = createPiece(Team.WHITE);

        assertThat(piece.touch()).isSameAs(piece);
    }

    private Piece createPiece(Team team, Move... moves) {
        return new PieceTest.PieceImplement(team, Set.of(moves));
    }
}
