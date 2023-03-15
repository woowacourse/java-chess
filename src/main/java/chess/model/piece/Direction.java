package chess.model.piece;

import java.util.Arrays;
import java.util.List;

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
    SOUTH_EAST_EAST(2, -1);

    private static final List<Direction> ALL_DIRECTIONS = List.of(
            NORTH, WEST, SOUTH, EAST,
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    );
    private static final List<Direction> DIAGONAL = List.of(
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    );
    private static final List<Direction> ORTHOGONAL = List.of(NORTH, WEST, SOUTH, EAST);
    
    private final int rank;
    private final int file;

    Direction(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public static List<Direction> knight() {
        return List.of(
                NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                NORTH_WEST_WEST, NORTH_EAST_EAST, SOUTH_WEST_WEST, SOUTH_EAST_EAST
        );
    }

    public static List<Direction> whitePawn() {
        return List.of(NORTH);
    }

    public static List<Direction> blackPawn() {
        return List.of(SOUTH);
    }

    public static List<Direction> king() {
        return ALL_DIRECTIONS;
    }

    public static List<Direction> queen() {
        return ALL_DIRECTIONS;
    }

    public static List<Direction> rook() {
        return ORTHOGONAL;
    }

    public static List<Direction> bishop() {
        return DIAGONAL;
    }

    public static Direction findDirection(final int rank, final int file) {
        return Arrays.stream(values())
                .filter(direction -> direction.match(rank, file))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Direction을 찾을 수 없습니다."));
    }

    private int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public boolean match(final int rank, final int file) {
        int gcd = gcd(Math.abs(rank), Math.abs(file));

        return this.rank == (rank / gcd) && this.file == (file / gcd);
    }
}
