package domain.piece;

<<<<<<< HEAD
import domain.game.Movement;
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;

public class Queen extends Piece {
    private static final Score SCORE = new Score(9);

=======
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    private Queen(Side side) {
        super(side);
    }

    public static Queen createOfWhite() {
        return new Queen(Side.WHITE);
    }

    public static Queen createOfBlack() {
        return new Queen(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isDiagonal() || movement.isPerpendicular();
    }

    @Override
<<<<<<< HEAD
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_QUEEN;
        }
        return PieceCategory.BLACK_QUEEN;
    }

    @Override
    public Score getScore() {
        return SCORE;
=======
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (movement.isOneStep()) {
            return Collections.emptyList();
        }
        return sourcePosition.getPath(targetPosition);
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }
}
