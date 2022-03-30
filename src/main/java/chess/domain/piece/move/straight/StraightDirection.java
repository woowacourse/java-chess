package chess.domain.piece.move.straight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.board.Route;
import chess.domain.piece.move.Direction;

public enum StraightDirection implements Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHWEST(-1, 1),
    SOUTHWEST(-1, -1),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    ;

    private final int dx;
    private final int dy;

    StraightDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static StraightDirection find(Route route) {
        int dx = toDegree(route.subtractHorizontal());
        int dy = toDegree(route.subtractVertical());
        return of(dx, dy);
    }

    private static int toDegree(int number) {
        return Integer.compare(number, 0);
    }

    private static StraightDirection of(int dx, int dy) {
        return Arrays.stream(values())
            .filter(value -> value.dx == dx)
            .filter(value -> value.dy == dy)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 방향이 없습니다."));
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public int getDy() {
        return dy;
    }

    public boolean isCross() {
        return getCross().contains(this);
    }

    public static List<StraightDirection> getCross() {
        return List.of(NORTH, SOUTH, EAST, WEST);
    }

    public static List<StraightDirection> getDiagonal() {
        return List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHEAST);
    }

    public static List<StraightDirection> getAll() {
        return Stream.concat(getCross().stream(), getDiagonal().stream())
            .collect(Collectors.toList());
    }
}
