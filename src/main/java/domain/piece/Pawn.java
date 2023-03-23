package domain.piece;

import domain.game.Movement;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Side;
import java.util.Collections;
import java.util.List;

public final class Pawn extends Piece {

    private Pawn(Side side) {
        super(side);
    }

    public static Pawn createOfBlack() {
        return new Pawn(Side.BLACK);
    }

    public static Pawn createOfWhite() {
        return new Pawn(Side.WHITE);
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (movement.isOneStep()) {
            return Collections.emptyList();
        }
        return sourcePosition.getPath(targetPosition);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (isWhite()) {
            return isMovableForWhite(targetPiece, movement, sourcePosition);
        }
        return isMovableForBlack(targetPiece, movement, sourcePosition);
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    private boolean isWhite() {
        return super.side.equals(Side.WHITE);
    }

    private boolean isMovableForWhite(Piece targetPiece, Movement movement, Position sourcePosition) {
        if (!targetPiece.isEmptyPiece()) {
            return isPossibleToAttackForWhite(targetPiece, movement);
        }
        if (isFirstMovement(sourcePosition)) {
            return isUnderTwoStepUpward(movement);
        }
        return isOneStepUpward(movement);
    }

    private boolean isMovableForBlack(Piece targetPiece, Movement movement, Position sourcePosition) {
        if (!targetPiece.isEmptyPiece()) {
            return isPossibleToAttackForBlack(targetPiece, movement);
        }
        if (isFirstMovement(sourcePosition)) {
            return isUnderTwoStepDownward(movement);
        }
        return isOneStepDownward(movement);
    }

    private boolean isPossibleToAttackForWhite(Piece targetPiece, Movement movement) {
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return isOneStepUpwardDiagonal(movement);
    }

    private boolean isPossibleToAttackForBlack(Piece targetPiece, Movement movement) {
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return isOneStepDownwardDiagonal(movement);
    }

    private boolean isOneStepUpwardDiagonal(Movement movement) {
        return movement.isOneStep() && movement.isDiagonal() && movement.isUpward();
    }

    private boolean isOneStepDownwardDiagonal(Movement movement) {
        return movement.isOneStep() && movement.isDiagonal() && movement.isDownward();
    }

    private boolean isUnderTwoStepUpward(Movement movement) {
        return movement.isUnderTwoSteps() &&
                movement.isPerpendicular() &&
                movement.isUpward();
    }

    private boolean isFirstMovement(Position sourcePosition) {
        return (this.side.equals(Side.WHITE) && sourcePosition.hasRankOf(Rank.TWO))
                || (this.side.equals(Side.BLACK) && sourcePosition.hasRankOf(Rank.SEVEN));
    }

    private static boolean isOneStepUpward(Movement movement) {
        return movement.isOneStep() &&
                movement.isPerpendicular() &&
                movement.isUpward();
    }

    private boolean isOneStepDownward(Movement movement) {
        return movement.isOneStep() &&
                movement.isPerpendicular() &&
                movement.isDownward();
    }

    private boolean isUnderTwoStepDownward(Movement movement) {
        return movement.isUnderTwoSteps() &&
                movement.isPerpendicular() &&
                movement.isDownward();
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_PAWN;
        }
        return PieceCategory.BLACK_PAWN;
    }

}
