package chess.domain.piece;

import chess.domain.File;
import chess.domain.Rank;

import java.util.Arrays;

public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_DOWN_RIGHT(1, -2),
    ;

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction find(final int dx, final int dy) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameGradiant(dx, dy) && direction.isSameSign(dx, dy))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이동 방향입니다."));
    }

    public File moveFile(final File currentFile) {
        return currentFile.moveByOffset(this.x);
    }

    public Rank moveRank(final Rank currentRank) {
        return currentRank.moveByOffset(this.y);
    }

    private boolean isSameGradiant(int dx, int dy) {
        return (double) this.x / this.y == (double) dx / dy;
    }

    private boolean isSameSign(int dx, int dy) {
        return this.x * dx >= 0 && this.y * dy >= 0;
    }
}
