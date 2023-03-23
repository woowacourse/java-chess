package domain.position;

import java.util.Arrays;

public enum Direction {
    N(1, 0),
    S(-1, 0),
    E(0, 1),
    W(0, -1),
    NE(1, 1),
    NW(1, -1),
    SE(-1, 1),
    SW(-1, -1),
    NOTHING(0, 0),
    ;

    private final int rankDifference;
    private final int fileDifference;

    Direction(final int rankDifference, final int fileDifference) {
        this.rankDifference = rankDifference;
        this.fileDifference = fileDifference;
    }

    public static Direction of(final Position from, final Position to) {
        if (!isDiagonal(from, to) && !isStraight(from, to)) {
            return NOTHING;
        }

        final int rank = from.compareRank(to);
        final int file = from.compareFile(to);

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rankDifference == rank && direction.fileDifference == file)
                .findAny()
                .orElse(NOTHING);
    }

    public static boolean isDiagonal(final Position from, final Position to) {
        final int fileDifference = from.getFileDifference(to);
        final int rankDifference = from.getRankDifference(to);

        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public static boolean isStraight(final Position from, final Position to) {
        return from.getFileDifference(to) == 0 ||
                from.getRankDifference(to) == 0;
    }

    public int getRankDifference() {
        return rankDifference;
    }

    public int getFileDifference() {
        return fileDifference;
    }
}
