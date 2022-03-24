package chess.domain.piece;

import chess.domain.board.Board;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static Direction findDirection(Position start, Position target) {
        for (Direction direction : getEightStraightDirections()) {
            Optional<Position> optionalPosition = IntStream
                    .rangeClosed(1, Position.calculateStraightDistance(start, target))
                    .mapToObj(number -> new Position(start.getX() + direction.getXDegree() * number,
                            start.getY() + direction.getYDegree() * number))
                    .filter(position -> position.equals(target))
                    .findAny();

            if (optionalPosition.isEmpty()) {
                continue;
            }

            return direction;
        }
        throw new IllegalArgumentException("해당 위치로 가는 방향을 찾을 수 없습니다.");
    }

    public static List<Direction> getKnightDirections() {
        return List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> getDiagonalDirections() {
        return List.of(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> getPerpendicularDirections() {
        return List.of(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> getEightStraightDirections() {
        return List.of(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST);
    }

    public static List<Direction> getBlackPawnAttackDirections() {
        return List.of(SOUTHWEST, SOUTHEAST);
    }

    public static List<Direction> getWhitePawnAttackDirections() {
        return List.of(NORTHWEST, NORTHEAST);
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }
}
