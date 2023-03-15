package chess.domain.piece;

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

        @Override
        protected boolean compareMove(Move pieceMove, Move move) {
            return false;
        }
    }

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece piece = createPiece(true);
        Piece otherPiece = createPiece(false);

        assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }

    private Piece createPiece(boolean isWhite, Move... moves) {
        return new PieceTest.PieceImplement(isWhite, Set.of(moves));
    }
}
