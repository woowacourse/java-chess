package chess.domain;

import chess.exception.ImpossibleMoveException;

public class Movement {
    private final Position sourcePosition;
    private final Position targetPosition;

    public Movement(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition, targetPosition);
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new ImpossibleMoveException("시작 위치와 목표 위치가 동일할 수 없습니다.");
        }
    }

    public int findRowDirection() {
        return findDirection(calculateRowDifference());
    }

    public int findColumnDirection() {
        return findDirection(calculateColumnDifference());
    }

    public int maxAbsoluteMoveDifference() {
        return Math.max(Math.abs(calculateRowDifference()), Math.abs(calculateColumnDifference()));
    }

    public int calculateRowDifference() {
        return sourcePosition.calculateRowDifference(targetPosition);
    }

    public int calculateColumnDifference() {
        return sourcePosition.calculateColumnDifference(targetPosition);
    }

    private int findDirection(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    public Position source() {
        return sourcePosition;
    }

    public Position target() {
        return targetPosition;
    }
}
