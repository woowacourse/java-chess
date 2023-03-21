package chess.model.position;

import java.util.Arrays;
import java.util.Set;

public enum Direction {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    NORTH_WEST_WEST(-2, 1),
    NORTH_EAST_EAST(2, 1),
    SOUTH_WEST_WEST(-2, -1),
    SOUTH_EAST_EAST(2, -1),

    ILLEGAL_MOVE(100, 100);

    private static final Set<Direction> ALL_DIRECTIONS = Set.of(
            NORTH, WEST, SOUTH, EAST,
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    );
    private static final Set<Direction> DIAGONAL = Set.of(
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    );
    private static final Set<Direction> ORTHOGONAL = Set.of(NORTH, WEST, SOUTH, EAST);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Set<Direction> allDirections() {
        return ALL_DIRECTIONS;
    }

    public static Set<Direction> orthogonal() {
        return ORTHOGONAL;
    }

    public static Set<Direction> diagonal() {
        return DIAGONAL;
    }

    public static Direction findDirection(final int rank, final int file) {
        return Arrays.stream(values())
                .filter(direction -> direction.match(file, rank))
                .findFirst()
                .orElse(ILLEGAL_MOVE);
    }

    public static boolean isDiagonal(final Direction direction) {
        return DIAGONAL.contains(direction);
    }

    public static boolean isUpOrDown(final Direction direction) {
        final Set<Direction> upAndDown = Set.of(SOUTH, NORTH);
        return upAndDown.contains(direction);
    }

    private int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public boolean match(final int file, final int rank) {
        int gcd = gcd(Math.abs(rank), Math.abs(file));

        return this.rank == (rank / gcd) && this.file == (file / gcd);
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
