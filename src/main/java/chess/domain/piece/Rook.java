package chess.domain.piece;

import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofRook()));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
