package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class Pawn extends AbstractPawn {
    protected Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
