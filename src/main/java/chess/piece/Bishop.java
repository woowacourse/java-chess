package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (from.isDiagonalWay(to)) {
            return MovementCondition.UNOBSTRUCTED;
        }
        return MovementCondition.IMPOSSIBLE;
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("3");
    }
}
