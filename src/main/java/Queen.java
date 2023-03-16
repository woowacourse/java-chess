import java.util.List;

public class Queen extends Piece {
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
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        return null;
    }
}
