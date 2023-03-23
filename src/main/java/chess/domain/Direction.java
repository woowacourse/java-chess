package chess.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    SOUTH_EAST(1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),

    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1),
    EAST_NORTH_NORTH(1, 2),
    WEST_NORTH_NORTH(-1, 2),
    EAST_SOUTH_SOUTH(1, -2),
    WEST_SOUTH_SOUTH(-1, -2);

    private final int fileDisplacement;
    private final int rankDisplacement;

    Direction(final int fileDisplacement, final int rankDisplacement) {
        this.fileDisplacement = fileDisplacement;
        this.rankDisplacement = rankDisplacement;
    }

    public static Optional<Direction> find(Position start, Position next) {
        return Arrays.stream(values())
                .filter(direction -> direction.fileDisplacement == start.fileIncreaseFrom(next)
                        && direction.rankDisplacement == start.rankIncreaseFrom(next))
                .findAny();
    }

    public int nextFile(int currentFile) {
        return currentFile + fileDisplacement;
    }

    public int nextRank(int currentRank) {
        return currentRank + rankDisplacement;
    }

}
