package chess.domain.move;

import chess.domain.piece.position.Position;

public class StraightMove implements Move {
    private static final int ZERO = 0;

    private final Direction direction;
    private final int count;

    StraightMove(Position source, Position target) {
        this.direction = findStraightMove(source, target);
        this.count = findDistance(source, target);
    }

    private int findDistance(Position source, Position target) {
        return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
    }

    private Direction findStraightMove(Position source, Position target) {
        if (source.isSameXPosition(target)) {
            if (isDownMove(source, target)) {
                return Direction.DOWN;
            }
            return Direction.UP;
        }
        if (isLeftMove(source, target)) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }

    private boolean isLeftMove(Position source, Position target) {
        return source.calculateFileDistance(target) > ZERO;
    }

    private boolean isDownMove(Position source, Position target) {
        return source.calculateRankDistance(target) > ZERO;
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
