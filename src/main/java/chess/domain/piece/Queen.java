package chess.domain.piece;

import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofQueen()));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
