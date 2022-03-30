package chess.domain.direction;

import chess.domain.position.Position;
import java.util.List;

public class DirectionDecider {

    public static Direction generateUnitPosition(List<Direction> directions, Position from, Position to) {
        return directions.stream()
                .filter(direction -> direction.isValidDirection(from, to))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
