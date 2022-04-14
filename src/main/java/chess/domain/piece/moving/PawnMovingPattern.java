package chess.domain.piece.moving;

import chess.domain.position.Position;

public abstract class PawnMovingPattern implements MovingPattern {

    @Override
    public boolean canMove(Position source, Position target, boolean hasTargetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = source.calculateRowDifferenceTo(target);
        Movement movement = Movement.find(columnDifference, rowDifference);

        if (!isPossibleMoving(movement)) {
            return false;
        }
        if (movement.isDiagonal()) {
            return hasTargetPiece;
        }
        if (movement.is2Step()) {
            return !hasTargetPiece && isCorrectTwoStepMovement(source);
        }
        return !hasTargetPiece;
    }

    protected abstract boolean isPossibleMoving(Movement movement);

    protected abstract boolean isCorrectTwoStepMovement(Position source);
}
