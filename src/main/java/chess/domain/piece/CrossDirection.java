package chess.domain.piece;

import java.util.Arrays;

public enum CrossDirection {
    TOP(0, 1),
    BOTTOM(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1);

    private static final String NO_DIRECTION_ERROR_GUIDE_MESSAGE = "일치하는 Direction 값이 없습니다";
    private final int gapOfColumn;
    private final int gapOfRank;

    CrossDirection(int gapOfColumn, int gapOfRank) {
        this.gapOfColumn = gapOfColumn;
            this.gapOfRank = gapOfRank;
    }

    public static CrossDirection findDirectionByGap(int gapOfColumn, int gapOfRank) {
        int absColumnGap = Math.abs(gapOfColumn);
        int absRankGap = Math.abs(gapOfRank);
        int bigger = Math.max(absColumnGap, absRankGap);

        return Arrays.stream(CrossDirection.values())
                .filter(direction -> direction.gapOfColumn * bigger == gapOfColumn && direction.gapOfRank * bigger == gapOfRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_GUIDE_MESSAGE));
    }

    public static boolean isCrossDirection(int distanceOfColumns, int distanceOfRanks) {
        int absColumnGap = Math.abs(distanceOfColumns);
        int absRankGap = Math.abs(distanceOfRanks);
        int bigger = Math.max(absColumnGap, absRankGap);

        return Arrays.stream(CrossDirection.values())
                .anyMatch(direction -> direction.gapOfColumn * bigger == distanceOfColumns && direction.gapOfRank * bigger == distanceOfRanks);
    }

    public int getGapOfColumn() {
        return gapOfColumn;
    }

    public int getGapOfRank() {
        return gapOfRank;
    }

}
