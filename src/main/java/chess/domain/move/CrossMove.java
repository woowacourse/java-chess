package chess.domain.move;

import chess.domain.piece.position.Position;

public class CrossMove implements Move {
    private static final int ZERO = 0;

    private final Direction direction;
    private final int count;

    public CrossMove(Position source, Position target) {
        this.direction = findCrossDirection(source, target);
        this.count = findDistance(source, target);
    }

    private int findDistance(Position source, Position target) {
        return Math.abs(source.calculateRankDistance(target));
    }

    private Direction findCrossDirection(Position source, Position target) {
        if (isUpRightDirection(source, target)) {
            return Direction.UP_RIGHT;
        }
        if (isUpLeftDirection(source, target)) {
            return Direction.UP_LEFT;
        }
        if (isDownLeftDirection(source, target)) {
            return Direction.DOWN_LEFT;
        }
        return Direction.DOWN_RIGHT;
    }

    private boolean isDownLeftDirection(Position source, Position target) {
        return source.calculateFileDistance(target) > ZERO && source.calculateRankDistance(target) > ZERO;
    }

    private boolean isUpLeftDirection(Position source, Position target) {
        return source.calculateFileDistance(target) > ZERO && source.calculateRankDistance(target) < ZERO;
    }

    private boolean isUpRightDirection(Position source, Position target) {
        return source.calculateFileDistance(target) < ZERO && source.calculateRankDistance(target) < ZERO;
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
