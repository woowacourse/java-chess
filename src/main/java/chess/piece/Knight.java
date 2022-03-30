package chess.piece;

import static chess.piece.movementcondition.BaseMovementCondition.IMPOSSIBLE;
import static chess.piece.movementcondition.BaseMovementCondition.POSSIBLE;

import chess.piece.movementcondition.MovementCondition;
import chess.position.Position;
import java.math.BigDecimal;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (isPossibleMovement(from, to)) {
            return POSSIBLE;
        }
        return IMPOSSIBLE;
    }

    private boolean isPossibleMovement(Position from, Position to) {
        int horizontalDistance = from.getHorizontalDistance(to);
        int verticalDistance = from.getVerticalDistance(to);
        return (horizontalDistance == 1 && verticalDistance == 2) ||
                (horizontalDistance == 2 && verticalDistance == 1);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("2.5");
    }
}
