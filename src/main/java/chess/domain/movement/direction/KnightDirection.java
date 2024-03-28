package chess.domain.movement.direction;

import chess.domain.position.MoveDistance;
import chess.domain.position.Position;
import java.util.List;

public class KnightDirection implements Direction {

    private final List<MoveDistance> points = List.of(
            new MoveDistance(1, 2),
            new MoveDistance(1, -2),
            new MoveDistance(-1, 2),
            new MoveDistance(-1, -2),
            new MoveDistance(2, 1),
            new MoveDistance(2, -1),
            new MoveDistance(-2, 1),
            new MoveDistance(-2, -1));

    @Override
    public boolean canReach(final Position source, final Position target, final List<Position> pieces) {
        return getPossiblePosition(source)
                .stream()
                .filter(position -> !pieces.contains(position))
                .anyMatch(position -> position.equals(target));
    }

    private List<Position> getPossiblePosition(final Position position) {
        return points.stream()
                .filter(position::isNextPositionInRange)
                .map(position::moveByDistance)
                .toList();
    }
}

