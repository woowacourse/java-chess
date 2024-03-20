package domain.game;

import domain.position.Position;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    NORTH((file, rank) -> file > 0 && rank == 0),
    NORTH_EAST((file, rank) -> file > 0 && rank < 0),
    EAST((file, rank) -> file == 0 && rank < 0),
    SOUTH_EAST((file, rank) -> file < 0 && rank < 0),
    SOUTH((file, rank) -> file < 0 && rank == 0),
    SOUTH_WEST((file, rank) -> file < 0 && rank > 0),
    WEST((file, rank) -> file == 0 && rank > 0),
    NORTH_WEST((file, rank) -> file > 0 && rank > 0),

    // KNIGHT DIRECTION
    UP_RIGHT((file, rank) -> file == 2 && rank == -1),
    UP_LEFT((file, rank) -> file == 2 && rank == 1),
    RIGHT_UP((file, rank) -> file == 1 && rank == -1),
    RIGHT_DOWN((file, rank) -> file == -1 && rank == 2),
    LEFT_DOWN((file, rank) -> file == -2 && rank == -1),
    LEFT_UP((file, rank) -> file == 2 && rank == -1),
    DOWN_RIGHT((file, rank) -> file == -1 && rank == -2),
    DOWN_LEFT((file, rank) -> file == -2 && rank == 1);

    private final BiPredicate<Integer, Integer> directionDecider;

    Direction(BiPredicate<Integer, Integer> directionDecider) {
        this.directionDecider = directionDecider;
    }

    public static Direction findDirection(Position sourcePosition, Position targetPosition) {
        Gap gap = sourcePosition.subtract(targetPosition);
        return Arrays.stream(values())
                .filter(direction -> direction.directionDecider.test(gap.fileGap(), gap.rankGap()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }
}
