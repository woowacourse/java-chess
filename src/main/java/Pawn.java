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
            return isMovableForWhite(movement);
        }
        return isMovableForBlack(movement);
    }

    private boolean isWhite() {
        return super.side.equals(Side.WHITE);
    }

    private boolean isMovableForWhite(Movement movement) {
        return movement.isOneStep() && movement.isUpward();

    }

    private boolean isMovableForBlack(Movement movement) {
        return movement.isOneStep() && movement.isDownward();
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
