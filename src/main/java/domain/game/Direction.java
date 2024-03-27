package domain.game;

import domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    UP_RIGHT(1, 2),
    UP_LEFT(-1, 2),
    RIGHT_UP(2, 1),
    RIGHT_DOWN(2, -1),
    LEFT_DOWN(-2, -1),
    LEFT_UP(-2, 1),
    DOWN_RIGHT(1, -2),
    DOWN_LEFT(-1, -2),
    ;

    private final int fileVector;
    private final int rankVector;

    Direction(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public static Direction findDirection(final Position source, final Position target) {
        Vector vector = target.generateVectorToTargetPosition(source);
        Vector unitVector = vector.toUnitVector();

        return Arrays.stream(values())
                .filter(direction -> isSameDirection(direction, unitVector))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]움직일 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(final Direction direction, final Vector vector) {
        return direction.fileVector == vector.file() && direction.rankVector == vector.rank();
    }

    public boolean isForward() {
        return anyMatch(List.of(
                Direction.NORTH, Direction.SOUTH
        ));
    }

    public boolean isDiagonal() {
        return anyMatch(List.of(
                Direction.NORTH_EAST, Direction.NORTH_WEST,
                Direction.SOUTH_EAST, Direction.SOUTH_WEST
        ));
    }

    private boolean anyMatch(final List<Direction> directions) {
        return directions.stream()
                .anyMatch(this::equals);
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
