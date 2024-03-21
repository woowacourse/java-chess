package domain.piece;

import domain.movement.Direction;
import domain.movement.MovementValidation;

public enum Type {
    KING(null),
    QUEEN(null),
    BISHOP(null),
    KNIGHT(null),
    ROOK(null),
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
