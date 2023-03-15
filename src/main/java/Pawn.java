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

    public List<Position> getPath(Position sourcePosition, Position targetPosition) {
        Movement movement = targetPosition.calculateIncrement(sourcePosition);
//        if (isMovable(movement)) {
//
//        }
        throw new IllegalArgumentException("Pawn이 움직일 수 없는 위치입니다.");
    }

    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateIncrement(targetPosition);
        if (isWhite()) {
            return isMovableForWhite(movement, sourcePosition);
        }
        return isMovableForBlack(movement, sourcePosition);
    }

    private boolean isWhite() {
        return super.side.equals(Side.WHITE);
    }

    private boolean isMovableForWhite(Movement movement, Position sourcePosition) {
        if (isFirstMovement(sourcePosition)) {
            return isUnderTwoStepUpward(movement);
        }
        return isOneStepUpward(movement);
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

    private boolean isMovableForBlack(Movement movement, Position sourcePosition) {
        if (isFirstMovement(sourcePosition)) {
            return isUnderTwoStepDownward(movement);
        }
        return isOneStepDownward(movement);
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


//    private boolean isAttackMoving(Movement movement, Pawn targetPiece) {
//        if (side == 아래) {
//            return movement.isPawnAttackMoving && targetPiece.side == this.side;
//        }
//        return movement.isPawnBlackAttackMoving && targetPiece.side == this.side;
//    }
//
//    private boolean isAttack(Pawn targetPiece) {
//        return targetPiece.side == this.side;

//    }
}
