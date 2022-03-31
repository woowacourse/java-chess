package chess.domain.piece.movingstrategy;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Set;

public class KnightMovingStrategy extends MovingStrategy {

    private final Set<Direction> POSSIBLE_DIRECTIONS = Set.of(
            Direction.SEVEN_SHAPE
    );

    @Override
    boolean isPossibleStep(Position from, Position to) {
        return true;
    }

    @Override
    boolean isPossibleDirection(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        System.out.println("direction : " + direction);
        return POSSIBLE_DIRECTIONS.contains(direction);
    }
}
