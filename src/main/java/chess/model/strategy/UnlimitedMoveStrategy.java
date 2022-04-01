package chess.model.strategy;

import chess.model.Direction;
import chess.model.Position;

import java.util.List;

public class UnlimitedMoveStrategy implements MoveStrategy{

    private final List<Direction> directions;

    public UnlimitedMoveStrategy(List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public boolean movable(Position source, Position target, boolean isKill) {
        Direction direction = Direction.of(source, target);
        return directions.contains(direction);
    }
}
