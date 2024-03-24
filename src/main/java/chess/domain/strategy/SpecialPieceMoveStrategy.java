package chess.domain.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class SpecialPieceMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;
    private final int defaultMaxMoveDistance;

    protected SpecialPieceMoveStrategy(List<Direction> directions, int defaultMaxMoveDistance) {
        this.directions = directions;
        this.defaultMaxMoveDistance = defaultMaxMoveDistance;
    }

    @Override
    public Map<Direction, Queue<Position>> generateMovablePositions(Position position) {
        return directions.stream()
                .collect(Collectors.toMap(
                        direction -> direction,
                        direction -> generateMovablePositionsByDirection(position, direction)
                ));
    }

    private Queue<Position> generateMovablePositionsByDirection(Position currentPosition, Direction direction) {
        int movableMaxDistance = currentPosition.calculateMaxDistance(direction, defaultMaxMoveDistance);
        return new ArrayDeque<>(IntStream.rangeClosed(1, movableMaxDistance)
                .mapToObj(weight -> currentPosition.calculateNextPosition(direction, weight))
                .toList());
    }
}
