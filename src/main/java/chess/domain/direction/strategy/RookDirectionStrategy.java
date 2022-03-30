package chess.domain.direction.strategy;

import chess.domain.direction.BasicDirection;
import java.util.Arrays;
import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class RookDirectionStrategy implements DirectionStrategy {

    @Override
    public Optional<? extends Direction> find(Position from, Position to) {
        return Arrays.stream(BasicDirection.values())
                .filter(direction -> direction.confirm(from, to))
                .findAny();
    }
}
