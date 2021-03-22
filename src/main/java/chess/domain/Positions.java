package chess.domain;

public final class Positions {

    private final Position currentPosition;
    private final Position targetPosition;

    public Positions(Position currentPosition, Position targetPosition) {
        this.currentPosition = currentPosition;
        this.targetPosition = targetPosition;
    }

    public Position currentPosition() {
        return currentPosition;
    }

    public Position targetPosition() {
        return targetPosition;
    }
}
