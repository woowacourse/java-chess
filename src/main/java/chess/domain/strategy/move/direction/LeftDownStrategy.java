package chess.domain.strategy.move.direction;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LeftDownStrategy implements DirectionStrategy {

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> path = IntStream.range(target.getFile() + 1, source.getFile())
                .mapToObj(index -> Position.of(index, target.getRank() - (target.getFile() - index)))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(path);
    }
}
