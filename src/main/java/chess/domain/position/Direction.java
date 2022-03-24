package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    EAST((file, rank) -> file > 0 && rank == 0),
    WEST((file, rank) -> file < 0 && rank == 0),
    NORTH((file, rank) -> file == 0 && rank > 0),
    SOUTH((file, rank) -> file == 0 && rank < 0),
    NORTH_EAST((file, rank) -> file > 0 && rank > 0 && file == rank),
    NORTH_WEST((file, rank) -> file < 0 && rank > 0 && -file == rank),
    SOUTH_EAST((file, rank) -> file > 0 && rank < 0 && -file == rank),
    SOUTH_WEST((file, rank) -> file < 0 && rank < 0 && file == rank),
    KNIGHT_EAST_LEFT((file, rank) -> file == 2 && rank == 1),
    KNIGHT_EAST_RIGHT((file, rank) -> file == 2 && rank == -1),
    KNIGHT_WEST_LEFT((file, rank) -> file == -2 && rank == -1),
    KNIGHT_WEST_RIGHT((file, rank) -> file == -2 && rank == 1),
    KNIGHT_NORTH_LEFT((file, rank) -> file == -1 && rank == 2),
    KNIGHT_NORTH_RIGHT((file, rank) -> file == 1 && rank == 2),
    KNIGHT_SOUTH_LEFT((file, rank) -> file == 1 && rank == -2),
    KNIGHT_SOUTH_RIGHT((file, rank) -> file == -1 && rank == -2),
    NONE((file, rank) -> false),
    ;

    private final BiPredicate<Integer, Integer> condition;


    Direction(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static Direction of(final int file, final int rank) {
        return Arrays.stream(values())
            .filter(value -> value.condition.test(file, rank))
            .findFirst()
            .orElse(NONE);
    }
}
