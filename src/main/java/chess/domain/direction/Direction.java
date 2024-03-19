package chess.domain.direction;

import chess.domain.Position;
import java.util.List;
import java.util.stream.Stream;

abstract class Direction {
    private final int moveCount;

    Direction(int moveCount) {
        this.moveCount = moveCount;
    }

    boolean canReach(Position from, Position to, List<Position> obstacle) {
        return Stream.iterate(from, this::next)
                .takeWhile(now -> !obstacle.contains(now))
                .limit(moveCount)
                .anyMatch(position -> position.equals(to));
    }

    abstract Position next(Position from);
}
