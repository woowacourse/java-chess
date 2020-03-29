package chess.domain.move;

import chess.domain.Position;

public class CrossType implements MoveType {
    private final Direction direction;
    private final int count;

    CrossType(Position source, Position target) {
        this.direction = findDirection(source, target);
        this.count = findCount(source, target);
    }

    private int findCount(Position source, Position target) {
        return Math.abs(source.calculateYPositionDistance(target));
    }

    private Direction findDirection(Position source, Position target) {
        if (source.isRightDirection(target) && source.isUpDirection(target)) {
            return Direction.UP_RIGHT;
        }
        if (source.isUpDirection(target) && source.isLeftDirection(target)) {
            return Direction.UP_LEFT;
        }
        if (source.isDownDirection(target) && source.isLeftDirection(target)) {
            return Direction.DOWN_LEFT;
        }
        return Direction.DOWN_RIGHT;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
