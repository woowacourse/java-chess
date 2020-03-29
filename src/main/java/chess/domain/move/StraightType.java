package chess.domain.move;

import chess.domain.Position;

public class StraightType implements MoveType {
    private final Direction direction;
    private final int count;

    StraightType(Position source, Position target) {
        this.direction = findDirection(source, target);
        this.count = findCount(source, target);
    }

    private int findCount(Position source, Position target) {
        return Math.abs(source.calculateXPositionDistance(target) + source.calculateYPositionDistance(target));
    }

    private Direction findDirection(Position source, Position target) {
        if (source.isSameFile(target)) {
            if (source.isDownDirection(target)) {
                return Direction.DOWN;
            }
            return Direction.UP;
        }
        if (source.isLeftDirection(target)) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
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
