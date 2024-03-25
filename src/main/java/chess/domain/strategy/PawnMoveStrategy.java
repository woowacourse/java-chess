package chess.domain.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class PawnMoveStrategy implements MoveStrategy {

    private static final int DEFAULT_MAX_MOVE_DISTANCE = 1;

    private final List<Direction> directions;
    private final Row startRow;

    protected PawnMoveStrategy(List<Direction> directions, Row startRow) {
        this.directions = directions;
        this.startRow = startRow;
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
        int movableMaxDistance = currentPosition.calculateMaxDistance(direction, DEFAULT_MAX_MOVE_DISTANCE);
        if (currentPosition.isSameRow(startRow) && direction.isStraight()) {
            movableMaxDistance++;
        }
        return new ArrayDeque<>(IntStream.rangeClosed(1, movableMaxDistance)
                .mapToObj(weight -> currentPosition.calculateNextPosition(direction, weight))
                .toList());
    }
}
