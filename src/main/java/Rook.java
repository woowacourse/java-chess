import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    protected Rook(Side side) {
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

    public List<Position> getPath(Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (movement.isOneStep()) {
            return Collections.emptyList();
        }
        return sourcePosition.getPath(targetPosition);
    }
}
