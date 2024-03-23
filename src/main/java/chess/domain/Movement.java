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
