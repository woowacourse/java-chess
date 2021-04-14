package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {
    TOP(0, 1),
    RIGHT_TOP(1, 1),
    RIGHT(1, 0),
    RIGHT_BOTTOM(1, -1),
    BOTTOM(0, -1),
    LEFT_BOTTOM(-1, -1),
    LEFT(-1, 0),
    LEFT_TOP(-1, 1),

    DOUBLE_TOP(0, 2),
    DOUBLE_BOTTOM(0, -2),

    RTT(1, 2),
    RRT(2, 1),
    RRB(2, -1),
    RBB(1, -2),
    LBB(-1, -2),
    LLB(-2, -1),
    LLT(-2, 1),
    LTT(-1, 2);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction getDirectionFromWeight(int horizontalWeight, int verticalWeight) {
        final int x = distinctDirection(horizontalWeight);
        final int y = distinctDirection(verticalWeight);

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.x == x && direction.y == y)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int distinctDirection(int weight) {
        return Integer.compare(weight, 0);
    }

    public static List<Direction> all() {
        return Stream.concat(diagonal().stream(), straight().stream())
                .collect(Collectors.toList());
    }

    public static List<Direction> diagonal() {
        return Arrays.asList(
                RIGHT_TOP,
                LEFT_TOP,
                LEFT_BOTTOM,
                RIGHT_BOTTOM
        );
    }

    public static List<Direction> straight() {
        return Arrays.asList(
                TOP,
                RIGHT,
                BOTTOM,
                LEFT
        );
    }

    public static List<Direction> knight() {
        return Arrays.asList(
                RTT,
                RRT,
                RRB,
                RBB,
                LBB,
                LLB,
                LLT,
                LTT
        );
    }

    public static List<Direction> pawnBlack(boolean isFirstStep) {
        List<Direction> directions = Arrays.asList(
                LEFT_BOTTOM,
                BOTTOM,
                RIGHT_BOTTOM
        );

        if (isFirstStep) {
            return Stream.concat(directions.stream(), Stream.of(DOUBLE_BOTTOM))
                    .collect(Collectors.toList());
        }

        return directions;
    }

    public static List<Direction> pawnWhite(boolean isFirstStep) {
        List<Direction> directions = Arrays.asList(
                LEFT_TOP,
                TOP,
                RIGHT_TOP
        );

        if (isFirstStep) {
            return Stream.concat(directions.stream(), Stream.of(DOUBLE_TOP))
                    .collect(Collectors.toList());
        }

        return directions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
