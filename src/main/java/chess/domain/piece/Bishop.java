package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;

public class Bishop extends MultiShift {

    public Bishop(final Color color, final Square square) {
        super(color, PieceType.BISHOP, square);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.RIGHT_DOWN);
    }
}
