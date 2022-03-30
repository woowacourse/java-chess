package chess.piece;

import static chess.piece.movementcondition.BaseMovementCondition.IMPOSSIBLE;
import static chess.piece.movementcondition.BaseMovementCondition.MUST_OBSTACLE_FREE;

import chess.piece.movementcondition.MovementCondition;
import chess.position.Position;
import java.math.BigDecimal;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (isPossibleMovement(from, to)) {
            return MUST_OBSTACLE_FREE;
        }
        return IMPOSSIBLE;
    }

    private boolean isPossibleMovement(Position from, Position to) {
        return from.isDiagonalWay(to) || from.isVerticalWay(to) || from.isHorizontalWay(to);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("9");
    }
}
