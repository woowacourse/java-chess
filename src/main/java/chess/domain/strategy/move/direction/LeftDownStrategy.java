package chess.domain.strategy.move.direction;

import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.ArrayList;
import java.util.List;

public class LeftDownStrategy implements DirectionStrategy {

    @Override
    public List<Position> findPath(Position source, Position target) {
        List<Position> path = new ArrayList<>();

        for (int i = target.getFile() + 1; i < source.getFile(); i++) {
            path.add(Positions.of(i, target.getRank() - (target.getFile() - i)));
        }

        return path;
    }
}
