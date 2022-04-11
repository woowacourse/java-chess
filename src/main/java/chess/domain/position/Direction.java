package chess.domain.position;

import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),

    SSE(1, -2),
    SSW(-1, -2),
    NNE(1, 2),
    NNW(-1, 2),
    EES(2, -1),
    EEN(2, 1),
    WWS(-2, -1),
    WWN(-2, 1);


    private final int columnValue;
    private final int rowValue;

    Direction(final int columnValue, final int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public static Direction getDirection(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .filter(direction -> Math.atan2(direction.rowValue, direction.columnValue) == from.getAngle(to))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 있는 방향이 존재하지 않습니다."));
    }

    public static List<Direction> kingDirections() {
        return List.of(
                Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
                Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public static List<Direction> queenDirections() {
        return kingDirections();
    }

    public static List<Direction> rookDirections() {
        return List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    public static List<Direction> bishopDirections() {
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public static List<Direction> knightDirections() {
        return List.of(
                Direction.SSE, Direction.SSW,
                Direction.NNE, Direction.NNW,
                Direction.EES, Direction.EEN,
                Direction.WWS, Direction.WWN);
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color == Color.BLACK) {
            return List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        if (color == Color.WHITE) {
            return List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST);
        }
        throw new IllegalStateException("해당 폰의 디렉션은 존재하지 않습니다.");
    }

    public static List<Direction> pawnStraightDirection() {
        return List.of(Direction.SOUTH, Direction.NORTH);
    }

    public static List<Direction> pawnDiagonalDirection() {
        return List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }
}
