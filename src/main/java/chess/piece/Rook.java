package chess.piece;

import static chess.piece.movementcondition.BaseMovementCondition.IMPOSSIBLE;
import static chess.piece.movementcondition.BaseMovementCondition.MUST_OBSTACLE_FREE;

import chess.piece.movementcondition.MovementCondition;
import chess.position.Position;
import java.math.BigDecimal;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (from.isVerticalWay(to) || from.isHorizontalWay(to)) {
            return MUST_OBSTACLE_FREE;
        }
        return IMPOSSIBLE;
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("5");
    }
}
