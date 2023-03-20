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
        final int rankDifference = from.getRankDifference(to);
        final int fileDifference = from.getFileDifference(to);

        return of(rankDifference, fileDifference);
    }

    public static Direction of(final int rankDifference, final int fileDifference) {
        if (isNotEightDirection(rankDifference, fileDifference)) {
            return NOTHING;
        }

        final int rank = Integer.compare(rankDifference, 0);
        final int file = Integer.compare(fileDifference, 0);

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rankDifference == rank && direction.fileDifference == file)
                .findAny()
                .orElse(NOTHING);
    }

    private static boolean isNotEightDirection(final int rankDifference, final int fileDifference) {
        if (rankDifference == 0 || fileDifference == 0) {
            return false;
        }

        return Math.abs(rankDifference) != Math.abs(fileDifference);
    }

    public static boolean isDiagonal(final Position from, final Position to) {
        final Direction direction = of(from, to);

        return NE.equals(direction) || NW.equals(direction) || SE.equals(direction) || SW.equals(direction);
    }

    public static boolean isStraight(final Position from, final Position to) {
        final Direction direction = of(from, to);

        return N.equals(direction) || W.equals(direction) || E.equals(direction) || S.equals(direction);
    }

    public int getRankDifference() {
        return rankDifference;
    }

    public int getFileDifference() {
        return fileDifference;
    }
}
