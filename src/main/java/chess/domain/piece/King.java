package chess.domain.piece;

import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;

public class King extends Piece {

    public King(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofKing()));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
