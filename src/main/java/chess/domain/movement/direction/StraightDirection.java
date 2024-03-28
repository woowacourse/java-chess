package chess.domain.movement.direction;

import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;

abstract class StraightDirection implements Direction {

    private final int moveCount;

    protected StraightDirection(final int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean canReach(final Position source, final Position target, final List<Position> obstacle) {
        return Stream.iterate(next(source), this::next)
                .takeWhile(now -> !obstacle.contains(now))
                .limit(moveCount)
                .anyMatch(position -> position.equals(target));
    }

    abstract Position next(Position position);
}
