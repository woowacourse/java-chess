package chess.util;

import static chess.domain.board.position.Direction.DOWN;
import static chess.domain.board.position.Direction.DOWN_LEFT;
import static chess.domain.board.position.Direction.DOWN_RIGHT;
import static chess.domain.board.position.Direction.LEFT;
import static chess.domain.board.position.Direction.RIGHT;
import static chess.domain.board.position.Direction.UP;
import static chess.domain.board.position.Direction.UP_LEFT;
import static chess.domain.board.position.Direction.UP_RIGHT;

import chess.domain.board.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PositionUtil {

    private PositionUtil() {
    }

    public static List<Position> positionsStraightBetween(Position from, Position to) {
        if (!isStraightPath(from, to)) {
            return List.of();
        }
        return positionsBetween(from, to);
    }

    private static List<Position> positionsBetween(Position from, Position to) {
        List<Position> positions = new ArrayList<>();
        Position next = from.oneStepToward(to);
        while (next != to) {
            positions.add(next);
            next = next.oneStepToward(to);
        }
        return positions;
    }

    public static boolean isStraightPath(Position from, Position to) {
        return isHorizontalOrVertical(from, to) || isDiagonal(from, to);
    }

    public static boolean isHorizontalOrVertical(Position from, Position to) {
        return Stream.of(UP, DOWN, RIGHT, LEFT)
                .anyMatch(direction -> from.checkDirection(to, direction));
    }

    public static boolean isDiagonal(Position from, Position to) {
        return Stream.of(UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT)
                .anyMatch(direction -> from.checkDirection(to, direction));
    }
}
