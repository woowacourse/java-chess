package domain.piece;

<<<<<<< HEAD
import domain.game.Movement;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Score;
import domain.game.Side;
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import java.util.Collections;
import java.util.List;

public final class Pawn extends Piece {
<<<<<<< HEAD
    private static final Score SCORE = new Score(1);
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)

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

<<<<<<< HEAD
    @Override
    public boolean isEmptyPiece() {
        return false;
    }

=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    private boolean isWhite() {
        return super.side.equals(Side.WHITE);
    }

    private boolean isMovableForWhite(Piece targetPiece, Movement movement, Position sourcePosition) {
<<<<<<< HEAD
        if (!targetPiece.isEmptyPiece()) {
=======
        if (targetPiece.side != Side.NEUTRAL) {
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
            return isPossibleToAttackForWhite(targetPiece, movement);
        }
        if (isFirstMovement(sourcePosition)) {
            return isUnderTwoStepUpward(movement);
        }
        return isOneStepUpward(movement);
    }

    private boolean isMovableForBlack(Piece targetPiece, Movement movement, Position sourcePosition) {
<<<<<<< HEAD
        if (!targetPiece.isEmptyPiece()) {
=======
        if (targetPiece.side != Side.NEUTRAL) {
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
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
<<<<<<< HEAD
=======

>>>>>>> 3ad1dbf (refactor: 패키지 분리)
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

<<<<<<< HEAD
    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_PAWN;
        }
        return PieceCategory.BLACK_PAWN;
    }

    @Override
    public Score getScore() {
        return SCORE;
    }
=======

//    private boolean isAttackMoving(domain.piece.Movement movement, domain.piece.Pawn targetPiece) {
//        if (side == 아래) {
//            return movement.isPawnAttackMoving && targetPiece.side == this.side;
//        }
//        return movement.isPawnBlackAttackMoving && targetPiece.side == this.side;
//    }
//
//    private boolean isAttack(domain.piece.Pawn targetPiece) {
//        return targetPiece.side == this.side;

//    }
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
}
