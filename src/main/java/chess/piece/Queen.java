package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (isPossibleMovement(from, to)) {
            return MovementCondition.UNOBSTRUCTED;
        }
        return MovementCondition.IMPOSSIBLE;
    }

    private boolean isPossibleMovement(Position from, Position to) {
        return from.isDiagonalWay(to) || from.isVerticalWay(to) || from.isHorizontalWay(to);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("9");
    }
}
