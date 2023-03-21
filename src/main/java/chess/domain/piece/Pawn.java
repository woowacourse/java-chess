package chess.domain.piece;

import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofPawnByColor(color)));
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
