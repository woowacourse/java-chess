package chess.domain.strategy.move.direction;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class DownStrategy implements DirectionStrategy {
    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();

        for (int i = target.getRank() + 1; i < source.getRank(); i++) {
            path.add(Position.of(source.getFile(), i));
        }
        return path;
    }
}