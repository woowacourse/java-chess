package chess.domain;

public class Positions {
    private final Position sourcePosition;
    private final Position targetPosition;

    public Positions(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition, targetPosition);
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("시작 위치와 목표 위치가 동일할 수 없습니다.");
        }
    }

    public int calculateRowDifference() {
        return sourcePosition.calculateRowDifference(targetPosition);
    }

    public int calculateColumnDifference() {
        return sourcePosition.calculateColumnDifference(targetPosition);
    }

    public Position source() {
        return sourcePosition;
    }

    public Position target() {
        return targetPosition;
    }
}
