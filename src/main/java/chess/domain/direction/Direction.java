package chess.domain.direction;

import chess.domain.Position;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),

    UP_UP_LEFT(-1, 2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),
    ;

    private final int columnAmount;
    private final int rowAmount;

    Direction(final int columnAmount, final int rowAmount) {
        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
    }

    public Position move(Position position) {
        return position.move(columnAmount, rowAmount);
    }

    public boolean isDirection(Position position, Position wantPosition, boolean singleMovable) {
        if (position.equals(wantPosition)) {
            return false;
        }
        if (singleMovable) {
            return position.isMovable(columnAmount, rowAmount) && wantPosition.equals(move(position));
        }
        return isMovableToWantPosition(position, wantPosition);
    }

    private boolean isMovableToWantPosition(Position position, Position wantPosition) {
        if (position.equals(wantPosition)) {
            return true;
        }
        if (position.isMovable(columnAmount, rowAmount)) {
            return isMovableToWantPosition(move(position), wantPosition);
        }
        return false;
    }
}
