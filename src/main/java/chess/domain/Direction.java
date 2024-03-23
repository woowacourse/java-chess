package chess.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),

    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1),
    ;

    public static final Set<Direction> ofStraight = Set.of(NORTH, SOUTH, WEST, EAST);
    public static final Set<Direction> ofDiagonal = Set.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    public static final Set<Direction> ofAll = Stream.concat(ofStraight.stream(), ofDiagonal.stream())
            .collect(Collectors.toSet());
    public static final Set<Direction> ofBlackPawn = Set.of(Direction.SOUTH, Direction.SOUTH_WEST,
            Direction.SOUTH_EAST);
    public static final Set<Direction> ofWhitePawn = Set.of(Direction.NORTH, Direction.NORTH_WEST,
            Direction.NORTH_EAST);
    public static final Set<Direction> ofKnight = Set.of(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST,
            SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH,
            WEST_WEST_NORTH, WEST_WEST_SOUTH);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isDiagonal() {
        return ofDiagonal.contains(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
