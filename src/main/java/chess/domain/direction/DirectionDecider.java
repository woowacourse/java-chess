package chess.domain.direction;

import chess.domain.position.Position;
import java.util.List;

public class DirectionDecider {

    private static final String INVALID_DIRECTION = "현재 위치에서는 갈 수 없는 방향입니다.";

    public static Direction generateUnitPosition(List<Direction> directions, Position from, Position to) {
        return directions.stream()
                .filter(direction -> direction.isValidDirection(from, to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION));
    }
}
