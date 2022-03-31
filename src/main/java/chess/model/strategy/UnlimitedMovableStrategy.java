package chess.model.strategy;

import chess.model.board.Square;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.MoveType;
import java.util.List;

public class UnlimitedMovableStrategy implements MovableStrategy {

    private final List<Direction> movableDirections;

    public UnlimitedMovableStrategy(List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    public boolean movable(Square source, Square target, MoveType moveType) {
        try {
            Direction direction = source.findDirection(target);
            return movableDirections.contains(direction);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
