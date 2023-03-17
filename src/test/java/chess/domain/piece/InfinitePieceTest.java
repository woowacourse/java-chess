package chess.domain.piece;

import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InfinitePieceTest {

    private static class InfinitePieceImplement extends InfinitePiece {

        public InfinitePieceImplement(Color color, Set<Move> moves) {
            super(color, moves);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("가능한 수인지 판단한다(무한)")
    @Test
    void isValidMoveInfinite() {
        Move move = new Move(UP, RIGHT);

        Piece piece = createPiece(move);
        assertThat(piece.hasMove(new Move(UP, RIGHT, UP, RIGHT))).isTrue();
    }

    private Piece createPiece(Move... moves) {
        return new InfinitePieceImplement(WHITE, Set.of(moves));
    }
}
