package chess.piece;

import static java.util.Collections.unmodifiableSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {

    NORTH(0, 1),
    NORTH_NORTH(0, 2),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_SOUTH(0, -2),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1);

    private final int x;

    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Set<Direction> ofLinear() {
        return Set.of(NORTH, EAST, SOUTH, WEST);
    }

    public static Set<Direction> ofDiagonal() {
        return Set.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static Set<Direction> ofEvery() {
        return Stream.concat(ofLinear().stream(), ofDiagonal().stream())
                .collect(Collectors.toSet());
    }

    public static Set<Direction> ofKnight() {
        return Set.of(NORTH_NORTH_EAST, NORTH_NORTH_WEST,
                SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                EAST_EAST_NORTH, EAST_EAST_SOUTH,
                WEST_WEST_NORTH, WEST_WEST_SOUTH);
    }

    public static Set<Direction> ofFirstWhitePawn() {
        final var whitePawnDirections = new HashSet<>(ofWhitePawn());
        whitePawnDirections.add(NORTH_NORTH);

        return unmodifiableSet(whitePawnDirections);
    }

    public static Set<Direction> ofWhitePawn() {
        return Set.of(NORTH, NORTH_EAST, NORTH_WEST);
    }

    public static Set<Direction> ofFirstBlackPawn() {
        final var blackPawnDirections = new HashSet<>(ofBlackPawn());
        blackPawnDirections.add(SOUTH_SOUTH);

        return unmodifiableSet(blackPawnDirections);
    }

    public static Set<Direction> ofBlackPawn() {
        return Set.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    public static Direction valueOf(final int x, final int y) {
        return Arrays.stream(values())
                .filter(it -> it.x == x && it.y == y)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치입니다."));
    }

    public boolean isVertical() {
        return x == 0 && y != 0;
    }

    public boolean isDiagonal() {
        return ofDiagonal().contains(this);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "x=" + x +
                ", y=" + y +
                "} " + super.toString();
    }
}
