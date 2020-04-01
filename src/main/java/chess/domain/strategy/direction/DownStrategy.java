package chess.domain.strategy.direction;

import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class DownStrategy implements DirectionStrategy {
    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> path = IntStream.range(target.getRank() + 1, source.getRank())
                .mapToObj(index -> Position.of(source.getFile(), index))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(path);
    }
}