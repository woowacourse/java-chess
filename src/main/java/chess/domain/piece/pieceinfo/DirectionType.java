package chess.domain.piece.pieceinfo;

import java.util.Arrays;
import java.util.List;

public enum DirectionType {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1),
    KNIGHT_UP_RIGHT(-2, 1),
    KNIGHT_UP_LEFT(-2, -1),
    KNIGHT_RIGHT_UP(-1, 2),
    KNIGHT_RIGHT_DOWN(1, 2),
    KNIGHT_DOWN_RIGHT(2, 1),
    KNIGHT_DOWN_LEFT(2, -1),
    KNIGHT_LEFT_UP(-1, -2),
    KNIGHT_LEFT_DOWN(1, -2);

    private final int x;
    private final int y;

    DirectionType(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static List<DirectionType> straightDirections() {
        return Arrays.asList(DirectionType.UP, DirectionType.DOWN, DirectionType.LEFT, DirectionType.RIGHT);
    }

    public static List<DirectionType> diagonalStraightDirections() {
        return Arrays.asList(DirectionType.UP_LEFT, DirectionType.UP_RIGHT, DirectionType.DOWN_LEFT, DirectionType.DOWN_RIGHT);
    }

    public static List<DirectionType> allDirections() {
        return Arrays.asList(DirectionType.UP, DirectionType.DOWN, DirectionType.LEFT, DirectionType.RIGHT,
                DirectionType.UP_LEFT, DirectionType.UP_RIGHT, DirectionType.DOWN_LEFT, DirectionType.DOWN_RIGHT);
    }

    public static List<DirectionType> knightDirections() {
        return Arrays.asList(
                DirectionType.KNIGHT_UP_LEFT, DirectionType.KNIGHT_UP_RIGHT, DirectionType.KNIGHT_DOWN_LEFT, DirectionType.KNIGHT_DOWN_RIGHT,
                DirectionType.KNIGHT_LEFT_UP, DirectionType.KNIGHT_LEFT_DOWN, DirectionType.KNIGHT_RIGHT_UP, DirectionType.KNIGHT_RIGHT_DOWN);
    }

    public static List<DirectionType> attackWhitePawnDirections() {
        return Arrays.asList(
                DirectionType.UP_LEFT, DirectionType.UP_RIGHT);
    }

    public static List<DirectionType> attackBlackPawnDirections() {
        return Arrays.asList(
                DirectionType.DOWN_LEFT, DirectionType.DOWN_RIGHT);
    }
}
