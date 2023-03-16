package domain.piece;

<<<<<<< HEAD
import domain.game.Movement;
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;

public class Rook extends Piece {
    private static final Score SCORE = new Score(5);

=======
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    private Rook(Side side) {
        super(side);
    }

    public static Rook createOfWhite() {
        return new Rook(Side.WHITE);
    }

    public static Rook createOfBlack() {
        return new Rook(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isPerpendicular();
    }

    @Override
<<<<<<< HEAD
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_ROOK;
        }
        return PieceCategory.BLACK_ROOK;
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

    @Override
    public boolean isEmptyPiece() {
        return false;
    }
}
