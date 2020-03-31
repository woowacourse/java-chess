package chess.domain.move;

public class MovingInfo {
    private final Position startPosition;
    private final Position targetPosition;

    public MovingInfo(Position startPosition, Position targetPosition) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
