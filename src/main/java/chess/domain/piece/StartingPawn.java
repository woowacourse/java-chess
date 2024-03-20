package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class StartingPawn extends AbstractPawn {

    public StartingPawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
