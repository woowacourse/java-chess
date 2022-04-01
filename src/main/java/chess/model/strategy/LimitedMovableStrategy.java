package chess.model.strategy;

import chess.model.board.Square;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.Distance;
import chess.model.strategy.move.MoveType;
import java.util.List;

public class LimitedMovableStrategy implements MovableStrategy {
    private final List<Direction> movableDirections;
    private final int moveLimit;

    public LimitedMovableStrategy(List<Direction> movableDirections, int moveLimit) {
        this.movableDirections = movableDirections;
        this.moveLimit = moveLimit;
    }

    @Override
    public boolean movable(Square source, Square target, MoveType moveType) {
        try {
            Distance distance = source.getDistance(target);
            Direction direction = source.findDirection(target);
            return movableDirections.contains(direction) && distance.isInRange(moveLimit);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
