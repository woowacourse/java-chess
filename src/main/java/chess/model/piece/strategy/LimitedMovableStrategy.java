package chess.model.piece.strategy;

import chess.model.Direction;
import chess.model.piece.Piece;
import java.util.List;

public class LimitedMovableStrategy implements MovableStrategy {
    private final List<Direction> movableDirections;
    private final int moveLimit;

    public LimitedMovableStrategy(List<Direction> movableDirections, int moveLimit) {
        this.movableDirections = movableDirections;
        this.moveLimit = moveLimit;
    }

    @Override
    public boolean movable(Piece source, Piece target) {
        Direction directionTo = source.findDirectionTo(target);
        return movableDirections.contains(directionTo) &&
                !target.isAlly(source) && source.getDistance(target) <= moveLimit;
    }
}
