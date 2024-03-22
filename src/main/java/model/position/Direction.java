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

    // 하, 우, 상, 좌, 하우, 하좌, 상우, 상좌
    static final int[] DIRECTION_RANK = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    static final int[] DIRECTION_FILE = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

    private final int index;
    private final BiPredicate<Integer, Integer> predicate;

    Direction(final int index, final BiPredicate<Integer, Integer> predicate) {
        this.index = index;
        this.predicate = predicate;
    }


    public static Direction from(Position currentPosition, Position nextPosition) {
        return Arrays.stream(values())
                .filter(direction -> direction.predicate.test(
                        nextPosition.getRankIndex() - currentPosition.getRankIndex(),
                        nextPosition.getFileIndex() - currentPosition.getFileIndex()))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isSouth(int dRank, int dFile) {
        return (dRank > 0 && dFile == 0);
    }

    private static boolean isEast(int dRank, int dFile) {
        return (dRank == 0 && dFile > 0);
    }

    private static boolean isNorth(int dRank, int dFile) {
        return (dRank < 0 && dFile == 0);
    }

    private static boolean isWest(int dRank, int dFile) {
        return (dRank == 0 && dFile < 0);
    }

    private static boolean isSouthEast(int dRank, int dFile) {
        return (dRank > 0 && dFile > 0);
    }

    private static boolean isSouthWest(int dRank, int dFile) {
        return (dRank > 0 && dFile < 0);
    }

    private static boolean isNorthEast(int dRank, int dFile) {
        return (dRank < 0 && dFile > 0);
    }

    private static boolean isNorthWest(int dRank, int dFile) {
        return (dRank < 0 && dFile < 0);
    }

    public int getIndex() {
        return index;
    }
}
