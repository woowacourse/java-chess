package domain.piece;

<<<<<<< HEAD
import domain.game.Movement;
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
<<<<<<< HEAD
    private static final Score SCORE = new Score(2.5);

=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    private Knight(Side side) {
        super(side);
    }

    public static Knight createOfWhite() {
        return new Knight(Side.WHITE);
    }

    public static Knight createOfBlack() {
        return new Knight(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
<<<<<<< HEAD
        return movement.isThreeStepAndNotPerpendicular();
=======
        return movement.isCorrectForKnight();
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        return Collections.emptyList();
    }
<<<<<<< HEAD

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_KNIGHT;
        }
        return PieceCategory.BLACK_KNIGHT;
    }

    @Override
    public Score getScore() {
        return SCORE;
    }
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
}
