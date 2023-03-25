package chess.domain.piece;

import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;
import chess.domain.move.Move;

public class InfinitePieceTest extends AbstractTestFixture {

    private static class InfinitePieceImplement extends InfinitePiece {

        public InfinitePieceImplement(Team team, Set<Move> moves) {
            super(team, moves);
        }

        @Override
        public double score() {
            return 0;
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("가능한 수인지 판단한다(무한)")
    @Test
    void isValidMoveInfinite() {
        Move move = createMove(UP, RIGHT);

        Piece piece = createPiece(move);
        assertThat(piece.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isTrue();
    }

    private Piece createPiece(Move... moves) {
        return new InfinitePieceImplement(Team.WHITE, Set.of(moves));
    }
}
