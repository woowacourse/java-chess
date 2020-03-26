package chess.domain.movefactory;

import chess.domain.Position;

public class CrossType implements MoveType {
    private final Direction direction;
    private final int count;

    public CrossType(Position source, Position target) {
        this.direction = findDirection(source, target);
        this.count = findCount(source, target);
    }

    private int findCount(Position source, Position target) {
        return Math.abs(source.calculateRankDistance(target));
    }

    private Direction findDirection(Position source, Position target) {
        if (source.calculateFileDistance(target) < 0 && source.calculateRankDistance(target) < 0) {
            return Direction.UP_RIGHT;
        }
        if (source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) < 0) {
            return Direction.UP_LEFT;
        }
        if (source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) > 0) {
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
