package chess.model.strategy;

import chess.model.MoveType;
import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.position.Position;

import java.util.List;

public class LimitedMoveStrategy implements MoveStrategy {
    private final List<Direction> directions;
    private final List<Distance> distances;

    public LimitedMoveStrategy(List<Direction> directions, List<Distance> distances) {
        this.directions = directions;
        this.distances = distances;
    }

    @Override
    public boolean movable(Position source, Position target, MoveType moveType) {
        Direction direction = Direction.of(source, target);
        Distance distance = Distance.of(source, target, direction);
        return directions.contains(direction) && distances.contains(distance);
    }
}
