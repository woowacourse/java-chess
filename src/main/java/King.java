import java.util.List;

public class King extends Piece {
    private King(Side side) {
        super(side);
    }

    public static King createOfWhite() {
        return new King(Side.WHITE);
    }

    public static King createOfBlack() {
        return new King(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isOneStep();
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        return null;
    }
}
