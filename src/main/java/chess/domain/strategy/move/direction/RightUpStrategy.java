package chess.domain.strategy.move.direction;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class RightUpStrategy implements DirectionStrategy {

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();

        for (int i = target.getFile() - 1; i > source.getFile(); i--) {
            path.add(Position.of(i, target.getRank() - (target.getFile() - i)));
        }

        return path;
    }
}
