package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;

public class Rook extends MultiShift {
    public Rook(final Color color, Square square) {
        super(color, PieceType.ROOK, square);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT);
    }
}
