package techcourse.fp.chess.domain;

import java.util.List;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_TWO(0, 2),
    DOWN_TWO(0, -2),
    //Knight block
    UP_TWO_RIGHT(1, 2),
    UP_TWO_LEFT(-1, 2),
    DOWN_TWO_RIGHT(1, -2),
    DOWN_TWO_LEFT(-1, -2),
    RIGHT_TWO_UP(2, 1),
    RIGHT_TWO_DOWN(2, -1),
    LEFT_TWO_UP(-2, 1),
    LEFT_TWO_DOWN(-2, -1);

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    private final int file;
    private final int rank;

    public static List<Direction> ofQueen() {
        return List.of(UP, DOWN, RIGHT, LEFT,
                UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> ofKing() {
        return List.of(UP, DOWN, RIGHT, LEFT,
                UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> ofRook() {
        return List.of(UP, DOWN, RIGHT, LEFT);
    }

    public static List<Direction> ofBishop() {
        return List.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> ofKnight() {
        return List.of(UP_TWO_RIGHT, UP_TWO_LEFT, DOWN_TWO_RIGHT, DOWN_TWO_LEFT,
                RIGHT_TWO_UP, RIGHT_TWO_DOWN, LEFT_TWO_UP, LEFT_TWO_DOWN);
    }

    public static List<Direction> ofWhitePawn() {
        return List.of(UP_RIGHT, UP_LEFT, UP, UP_TWO);
    }

    public static List<Direction> ofBlackPawn() {
        return List.of(DOWN_LEFT, DOWN_RIGHT, DOWN, DOWN_TWO);
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
