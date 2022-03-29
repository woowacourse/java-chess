package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (from.isAdjacent(to)) {
            return MovementCondition.POSSIBLE;
        }
        return MovementCondition.IMPOSSIBLE;
    }

    @Override
    public BigDecimal getPoint() {
        return BigDecimal.ZERO;
    }
}
