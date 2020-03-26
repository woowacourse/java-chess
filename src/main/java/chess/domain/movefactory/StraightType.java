package chess.domain.movefactory;

import chess.domain.Position;

public class StraightType implements MoveType {
    private final Direction direction;
    private final int count;

    public StraightType(Position source, Position target) {
        this.direction = findDirection(source, target);
        this.count = findCount(source, target);
    }

    private int findCount(Position source, Position target) {
        return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
    }

    private Direction findDirection(Position source, Position target) {
        if (source.isSameFile(target)) {
            if (source.calculateRankDistance(target) > 0) {
                return Direction.DOWN;
            }
            return Direction.UP;
        }
        if (source.calculateFileDistance(target) > 0) {
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
