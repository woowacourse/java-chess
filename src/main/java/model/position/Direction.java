package model.position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    SOUTH(Direction::isSouth, 1, 0),
    EAST(Direction::isEast, 0, 1),
    NORTH(Direction::isNorth, -1, 0),
    WEST(Direction::isWest, 0, -1),
    SOUTH_EAST(Direction::isSouthEast, 1, 1),
    SOUTH_WEST(Direction::isSouthWest, 1, -1),
    NORTH_EAST(Direction::isNorthEast, -1, 1),
    NORTH_WEST(Direction::isNorthWest, -1, -1);

    private final BiPredicate<Integer, Integer> predicate;
    private final int deltaRank;
    private final int deltaFile;

    Direction(final BiPredicate<Integer, Integer> predicate, final int deltaRank, final int deltaFile) {
        this.predicate = predicate;
        this.deltaRank = deltaRank;
        this.deltaFile = deltaFile;
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

    public int getDeltaRank() {
        return deltaRank;
    }

    public int getDeltaFile() {
        return deltaFile;
    }
}
