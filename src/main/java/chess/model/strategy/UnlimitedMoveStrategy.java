package chess.model.strategy;

import chess.model.MoveType;
import chess.model.position.Direction;
import chess.model.position.Position;

import java.util.List;

public class UnlimitedMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;

    public UnlimitedMoveStrategy(List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public boolean movable(Position source, Position target, MoveType moveType) {
        Direction direction = Direction.of(source, target);
        return directions.contains(direction);
    }
}
