package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;

public class Queen extends MultiShift {
    public Queen(final Color color, final Square square) {
        super(color, PieceType.QUEEN, square);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
                Movement.LEFT_UP, Movement.RIGHT_UP, Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }
}
