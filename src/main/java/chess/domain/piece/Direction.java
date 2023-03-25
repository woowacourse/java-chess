package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Arrays;

public enum Direction {
    TOP(0, 1),
    BOTTOM(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1),
    KNIGHT_TOP_LEFT(-1, 2),
    KNIGHT_TOP_RIGHT(1, 2),
    KNIGHT_LEFT_TOP(-2, 1),
    KNIGHT_LEFT_BOTTOM(-2, -1),
    KNIGHT_RIGHT_TOP(2, 1),
    KNIGHT_RIGHT_BOTTOM(2, -1),
    KNIGHT_BOTTOM_LEFT(-1, -2),
    KNIGHT_BOTTOM_RIGHT(1, -2);

    private static final String NO_DIRECTION_ERROR_GUIDE_MESSAGE = "일치하는 Direction 값이 없습니다";
    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirectionByGap(Position start, Position end) {
        int distanceOfColumns = start.findGapOfColumn(end);
        int distanceOfRanks = start.findGapOfRank(end);
        if (hasExactlySameDirectionWith(distanceOfColumns, distanceOfRanks)) {
            return findDirectionHasExactlySameUnitVector(distanceOfColumns, distanceOfRanks);
        }
        return findDirectionHasSameUnitVector(distanceOfColumns, distanceOfRanks);
    }

    private static boolean hasExactlySameDirectionWith(int gapOfColumns, int gapOfRanks) {
        return Arrays.stream(values())
                .anyMatch(direction -> direction.x == gapOfColumns && direction.y == gapOfRanks);
    }

    private static Direction findDirectionHasExactlySameUnitVector(int distanceOfColumns, int distanceOfRanks) {
        return Arrays.stream(values())
                .filter(direction -> direction.x == distanceOfColumns && direction.y == distanceOfRanks)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_GUIDE_MESSAGE));
    }

    private static Direction findDirectionHasSameUnitVector(int distanceOfColumns, int distanceOfRanks) {
        int absX = Math.abs(distanceOfColumns);
        int absY = Math.abs(distanceOfRanks);
        int bigger = Math.max(absX, absY);
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.x * bigger == distanceOfColumns && direction.y * bigger == distanceOfRanks)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_GUIDE_MESSAGE));
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
