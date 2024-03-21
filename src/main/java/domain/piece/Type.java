package domain.piece;

import domain.movement.Direction;
import domain.movement.KnightMovementValidation;
import domain.movement.MovementValidation;
import domain.movement.RookMovementValidation;

public enum Type {
    KING(null),
    QUEEN(null),
    BISHOP(null),
    KNIGHT(new KnightMovementValidation()),
    ROOK(new RookMovementValidation()),
    PAWN(null);

    private final MovementValidation movementValidation;

    Type(MovementValidation movementValidation) {
        this.movementValidation = movementValidation;
    }

    public boolean isMovable(Direction direction) {
        return movementValidation.isMovable(direction);
    }

    public boolean isValidMoveCount(int moveCount) {
        return movementValidation.isValidMoveCount(moveCount);
    }
}
