package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    TOP(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    BOTTOM(0, -1),
    TOP_RIGHT(1, 1),
    TOP_LEFT(-1, 1),
    BOTTOM_RIGHT(1, -1),
    BOTTOM_LEFT(-1, -1),
    TTR(1, 2),
    TTL(-1, 2),
    BBR(1, -2),
    BBL(-1, -2),
    RRT(2, 1),
    RRB(2, -1),
    LLT(-2, 1),
    LLB(-2, -1);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction of(Position source, Position target) {
        File sourceFile = source.getFile();
        Rank sourceRank = source.getRank();

        File targetFile = target.getFile();
        Rank targetRank = target.getRank();

        int fileAbsoluteValue = sourceFile.calculateAbsoluteValue(targetFile);
        int rankAbsoluteValue = sourceRank.calculateAbsoluteValue(targetRank);

        if ((fileAbsoluteValue == 1 && rankAbsoluteValue == 2) || (fileAbsoluteValue == 2 && rankAbsoluteValue == 1)) {
            return getDirection(values(), sourceFile.calculateFile(targetFile), sourceRank.calculateRank(targetRank));
        }

        return getDirectionWithCalculate(source, target);
    }

    public boolean isKnightDirection() {
        List<Direction> knightDirection = List.of(TTR, TTL, BBR, BBL, RRT, RRB, LLT, LLB);

        return knightDirection.contains(this);
    }

    public boolean isLinearDirection() {
        List<Direction> linearDirection = List.of(TOP, LEFT, BOTTOM, RIGHT);

        return linearDirection.contains(this);
    }

    public boolean isDiagonalDirection() {
        List<Direction> diagonalDirection = List.of(TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT);

        return diagonalDirection.contains(this);
    }

    private static Direction getDirection(Direction[] values, int file, int rank) {
        return Arrays.stream(values)
                .filter(direction -> direction.getFile() == file && direction.getRank() == rank)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Direction getDirectionWithCalculate(Position source, Position target) {
        int file = calculateFileDirection(source, target);
        int rank = calculateRankDirection(source, target);

        return getDirection(values(), file, rank);
    }

    private static int calculateRankDirection(Position source, Position target) {
        Rank sourceRank = source.getRank();
        Rank targetRank = target.getRank();

        return Integer.compare(targetRank.calculateRank(sourceRank), 0);

    }

    private static int calculateFileDirection(Position source, Position target) {
        File sourceFile = source.getFile();
        File targetFile = target.getFile();

        return Integer.compare(targetFile.calculateFile(sourceFile), 0);

    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
