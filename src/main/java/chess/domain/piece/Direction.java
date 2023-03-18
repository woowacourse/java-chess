package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Arrays;

public enum Direction{
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
        int gapOfRanks = start.findGapOfRank(end);
        int gapOfColumns = start.findGapOfColum(end);
        int absX = Math.abs(gapOfColumns);
        int absY = Math.abs(gapOfRanks);

        return Arrays.stream(Direction.values())
                .filter(direction -> {
                    if(hasExactlySameDirectionWith(gapOfColumns, gapOfColumns)) {
                        return direction.x == gapOfColumns && direction.y == gapOfRanks;
                    }
                    return direction.x * absX ==  gapOfColumns && direction.y * absY == gapOfRanks;
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_GUIDE_MESSAGE));
    }

    private static boolean hasExactlySameDirectionWith(int gapOfColumns, int gapOfRanks) {
        return Arrays.stream(values())
                .anyMatch(direction -> direction.x == gapOfColumns && direction.y == gapOfRanks);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
