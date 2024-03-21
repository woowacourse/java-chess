package model.position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    SOUTH(0, Direction::isSouth),
    EAST(1, Direction::isEast),
    NORTH(2, Direction::isNorth),
    WEST(3, Direction::isWest),
    SOUTH_EAST(4, Direction::isSouthEast),
    SOUTH_WEST(5, Direction::isSouthWest),
    NORTH_EAST(6, Direction::isNorthEast),
    NORTH_WEST(7, Direction::isNorthWest);


    private final int index;
    private final BiPredicate<Integer, Integer> predicate;


    Direction(final int index, final BiPredicate<Integer, Integer> predicate) {
        this.index = index;
        this.predicate = predicate;
    }

    // 하, 우, 상, 좌, 하우, 하좌, 상우, 상좌
    public static final int[] dRow = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    public static final int[] dColumn = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

    public static Direction from(Position currentPosition, Position nextPosition) {
        return Arrays.stream(values())
                .filter(direction -> direction.predicate.test(
                        nextPosition.getRowIndex() - currentPosition.getRowIndex(),
                        nextPosition.getColumnIndex() - currentPosition.getColumnIndex()))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isSouth(int dRow, int dColumn) {
        return (dRow > 0 && dColumn == 0);
    }

    private static boolean isEast(int dRow, int dColumn) {
        return (dRow == 0 && dColumn > 0);
    }

    private static boolean isNorth(int dRow, int dColumn) {
        return (dRow < 0 && dColumn == 0);
    }

    private static boolean isWest(int dRow, int dColumn) {
        return (dRow == 0 && dColumn < 0);
    }

    private static boolean isSouthEast(int dRow, int dColumn) {
        return (dRow > 0 && dColumn > 0);
    }

    private static boolean isSouthWest(int dRow, int dColumn) {
        return (dRow > 0 && dColumn < 0);
    }

    private static boolean isNorthEast(int dRow, int dColumn) {
        return (dRow < 0 && dColumn > 0);
    }

    private static boolean isNorthWest(int dRow, int dColumn) {
        return (dRow < 0 && dColumn < 0);
    }

    public int getIndex() {
        return index;
    }
}
