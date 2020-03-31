package chess.position;

import java.util.Arrays;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    NONE(0, 0);

    private final int increaseAmountOfFile;
    private final int increaseAmountOfRank;

    Direction(int increaseAmountOfFile, int increaseAmountOfRank) {
        this.increaseAmountOfFile = increaseAmountOfFile;
        this.increaseAmountOfRank = increaseAmountOfRank;
    }

    public static Direction of(Position source, Position target) {
        return Arrays.stream(values())
                .filter(direction -> direction.increaseAmountOfFile == source.increaseAmountOfFile(target)
                        && direction.increaseAmountOfRank == source.increaseAmountOfRank(target))
                .findFirst()
                .orElse(NONE);
    }

    public int getIncreaseAmountOfFile() {
        return increaseAmountOfFile;
    }

    public int getIncreaseAmountOfRank() {
        return increaseAmountOfRank;
    }
}
