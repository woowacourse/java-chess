package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (from.isVerticalWay(to) || from.isHorizontalWay(to)) {
            return MovementCondition.UNOBSTRUCTED;
        }
        return MovementCondition.IMPOSSIBLE;
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("5");
    }
}
