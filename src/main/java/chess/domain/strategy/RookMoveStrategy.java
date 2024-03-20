package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RookMoveStrategy implements MoveStrategy{

    private static final int DEFAULT_MAX_DISTANCE = 7;

    private final List<Direction> directions = List.of(Direction.N, Direction.W, Direction.S, Direction.E);

    @Override
    public Map<Direction, List<Position>> generateMovablePositions(Position currentPosition) {
        return directions.stream()
                .collect(Collectors.toMap(
                        direction -> direction,
                        direction -> generateMovablePositionsByDirection(currentPosition, direction)
                ));
    }

    private List<Position> generateMovablePositionsByDirection(Position currentPosition, Direction direction) {
        int movableMaxDistance = currentPosition.calculateMaxDistance(direction, DEFAULT_MAX_DISTANCE);
        return IntStream.rangeClosed(1, movableMaxDistance)
                .mapToObj(weight -> currentPosition.calculateNextPosition(direction, weight))
                .toList();
    }
}
