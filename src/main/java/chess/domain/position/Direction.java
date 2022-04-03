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

    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    EAST_EAST_SOUTH(2, -1),
    EAST_EAST_NORTH(2, 1),
    WEST_WEST_SOUTH(-2, -1),
    WEST_WEST_NORTH(-2, 1);


    private final int columnValue;
    private final int rowValue;

    Direction(int columnValue, int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public static Direction getDirectionByPositions(Position source, Position target) {
        return Arrays.stream(values())
                .filter(direction -> Math.atan2(direction.rowValue, direction.columnValue)
                        == Math.atan2(source.getRowDifferenceWithTarget(target),
                        source.getColumnDifferenceWithTarget(target)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 위치로 가는 방향이 존재하지 않습니다."));
    }

    public static List<Direction> kingAndQueenDirections() {
        return List.of(
                Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
                Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public static List<Direction> rookDirections() {
        return List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    public static List<Direction> bishopDirections() {
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public static List<Direction> knightDirections() {
        return List.of(
                Direction.SOUTH_SOUTH_EAST, Direction.SOUTH_SOUTH_WEST,
                Direction.NORTH_NORTH_EAST, Direction.NORTH_NORTH_WEST,
                Direction.EAST_EAST_SOUTH, Direction.EAST_EAST_NORTH,
                Direction.WEST_WEST_SOUTH, Direction.WEST_WEST_NORTH);
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        if (color.isWhite()) {
            return List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST);
        }
        throw new IllegalStateException("해당 폰의 디렉션은 존재하지 않습니다.");
    }

    public boolean isSameDirection(Direction direction) {
        return this == direction;
    }

    public boolean isInDirections(List<Direction> directions) {
        return directions.contains(this);
    }

    public boolean isSameWithDistanceAndDirection(Position source, Position target) {
        return source.getColumnDifferenceWithTarget(target) == this.columnValue
                && source.getRowDifferenceWithTarget(target) == this.rowValue;
    }

    public boolean isPawnStraigtDirection() {
        return this == NORTH || this == SOUTH;
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }
}
