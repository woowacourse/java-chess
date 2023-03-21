package chess.domain.piece;

import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofBishop()));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
