package chess.domain.piece;

import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofKnight()));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
