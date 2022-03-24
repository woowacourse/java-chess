package chess.domain.board;

import java.util.List;

public enum Direction {

    NORTH(0,1),
    SOUTH(0,-1),
    EAST(1,0),
    WEST(-1,0),
    NORTH_EAST(1,1),
    NORTH_WEST(1,-1),
    SOUTH_EAST(-1,1),
    SOUTH_WEST(-1,-1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    EAST_NORTH_EAST(2, 1),
    EAST_SOUTH_EAST(2, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    WEST_SOUTH_WEST(-2, -1),
    WEST_NORTH_WEST(-2, 1),
    ;

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static List<Direction> getPawnDirections() {
        return List.of(NORTH);
    }

    public static List<Direction> getKnightDirections() {
        return List.of(
                NORTH_NORTH_EAST,
                NORTH_NORTH_WEST,
                EAST_NORTH_EAST,
                EAST_SOUTH_EAST,
                SOUTH_SOUTH_EAST,
                SOUTH_SOUTH_WEST,
                WEST_SOUTH_WEST,
                WEST_NORTH_WEST
        );
    }

    @Override
    public String toString() {
        return "Direction{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
