package chess.domain.strategy.move.direction;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class LeftDownStrategy implements DirectionStrategy {

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();

        for (int i = target.getFile() + 1; i < source.getFile(); i++) {
            path.add(Position.of(i, target.getRank() - (target.getFile() - i)));
        }

//        List<Position> path2 = IntStream.range(target.getFile() + 1, source.getFile())
//                .mapToObj(index -> Position.of(index, target.getRank() - (target.getFile() - index)))
//                .collect(Collectors.toList());

        return path;
    }
}
