package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class PawnMoveStrategy implements MoveStrategy {

    private static final int DEFAULT_MAX_MOVE_DISTANCE = 1;

    private final List<Direction> directions;
    private final Row startRow;
    private final Direction forwardDirection;

    public PawnMoveStrategy(List<Direction> directions, Row startRow, Direction forwardDirection) {
        this.directions = directions;
        this.startRow = startRow;
        this.forwardDirection = forwardDirection;
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
        int movableMaxDistance = currentPosition.calculateMaxDistance(direction, DEFAULT_MAX_MOVE_DISTANCE);
        if (currentPosition.isSameRow(startRow) && direction == forwardDirection) {
            movableMaxDistance++;
        }
        return new ArrayDeque<>(IntStream.rangeClosed(1, movableMaxDistance)
                .mapToObj(weight -> currentPosition.calculateNextPosition(direction, weight))
                .toList());
    }
}
