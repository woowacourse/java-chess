package chess.domain.strategy.speical;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class SpecialPieceMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;
    private final int defaultMaxMoveDistance;

    public SpecialPieceMoveStrategy(List<Direction> directions, int defaultMaxMoveDistance) {
        this.directions = directions;
        this.defaultMaxMoveDistance = defaultMaxMoveDistance;
    }

    @Override
    public Map<Direction, Deque<Position>> generateMovablePositions(Position position) {
        return directions.stream()
                .collect(Collectors.toMap(
                        direction -> direction,
                        direction -> generateMovablePositionsByDirection(position, direction)
                ));
    }

    private Deque<Position> generateMovablePositionsByDirection(Position currentPosition, Direction direction) {
        int movableMaxDistance = currentPosition.calculateMaxDistance(direction, defaultMaxMoveDistance);
        return new ArrayDeque<>(IntStream.rangeClosed(1, movableMaxDistance)
                .mapToObj(weight -> currentPosition.calculateNextPosition(direction, weight))
                .toList());
    }
}
