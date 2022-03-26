package chess.domain.move;

import chess.domain.position.File;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    EAST(((file, rank) -> file > 0 && rank == 0), 1, 0),
    WEST(((file, rank) -> file < 0 && rank == 0), -1, 0),
    NORTH(((file, rank) -> file == 0 && rank > 0), 0, 1),
    SOUTH(((file, rank) -> file == 0 && rank < 0), 0, -1),
    NORTH_EAST(((file, rank) -> file > 0 && rank > 0 && file == rank), 1, 1),
    NORTH_WEST(((file, rank) -> file < 0 && rank > 0 && -file == rank), -1, 1),
    SOUTH_EAST(((file, rank) -> file > 0 && rank < 0 && -file == rank), 1, -1),
    SOUTH_WEST(((file, rank) -> file < 0 && rank < 0 && file == rank), -1, -1),
    KNIGHT_EAST_LEFT(((file, rank) -> file == 2 && rank == 1), 2, 1),
    KNIGHT_EAST_RIGHT(((file, rank) -> file == 2 && rank == -1), 2, -1),
    KNIGHT_WEST_LEFT(((file, rank) -> file == -2 && rank == -1), -2, 1),
    KNIGHT_WEST_RIGHT(((file, rank) -> file == -2 && rank == 1), -2, -1),
    KNIGHT_NORTH_LEFT(((file, rank) -> file == -1 && rank == 2), -1, 2),
    KNIGHT_NORTH_RIGHT(((file, rank) -> file == 1 && rank == 2), 1, 2),
    KNIGHT_SOUTH_LEFT(((file, rank) -> file == 1 && rank == -2), 1, -2),
    KNIGHT_SOUTH_RIGHT(((file, rank) -> file == -1 && rank == -2), -1, -2),
    NONE(((file, rank) -> false), 0, 0),
    ;

    private final BiPredicate<Integer, Integer> condition;
    private final int fileDistance;
    private final int rankDistance;

    Direction(final BiPredicate<Integer, Integer> condition,
        final int fileDistance, final int rankDistance) {
        this.condition = condition;
        this.fileDistance = fileDistance;
        this.rankDistance = rankDistance;
    }

    public static Direction of(final int fileDistance, final int rankDistance) {
        return Arrays.stream(values())
            .filter(value -> value.condition.test(fileDistance, rankDistance))
            .findFirst()
            .orElse(NONE);
    }

    public File moveFile(final File file) {
        return file.move(fileDistance);
    }

    public Rank moveRank(final Rank rank) {
        return rank.move(rankDistance);
    }
}
