package chess.model.strategy;

import chess.model.Direction;
import chess.model.Distance;
import chess.model.Position;

import java.util.List;

public class LimitedMoveStrategy implements MoveStrategy {
    private final List<Direction> directions;
    private final List<Distance> distances;

    public LimitedMoveStrategy(List<Direction> directions, List<Distance> distances) {
        this.directions = directions;
        this.distances = distances;
    }

    @Override
    public boolean movable(Position source, Position target, boolean isKill) {
        Direction direction = Direction.of(source, target);
        Distance distance = Distance.of(source, target);
        return directions.contains(direction) && distances.contains(distance);
    }
}
