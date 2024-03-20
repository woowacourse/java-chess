package chess.domain.movement.direction;

import chess.domain.Position;
import java.util.List;
import java.util.stream.Stream;

abstract class StraightDirection implements Direction {
    private final int moveCount;

    StraightDirection(int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean canReach(Position from, Position to, List<Position> obstacle) {
        return Stream.iterate(from, this::next)
                .takeWhile(now -> !obstacle.contains(now))
                .limit(moveCount)
                .anyMatch(position -> position.equals(to));
    }

    abstract Position next(Position from);
}
