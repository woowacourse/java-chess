package chess.domain;

import java.util.Arrays;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(1, -1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(-1, 1),

    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),

    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),

    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),

    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    ;

    private static final String INVALID_DIRECTION = "움직일 수 없는 방향입니다.";

    private final int fileIndex;
    private final int rankIndex;

    Direction(final int fileIndex, final int rankIndex) {
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
    }

    public static Direction of(final int fileIndex, final int rankIndex) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.fileIndex == fileIndex && direction.rankIndex == rankIndex)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION));
    }

    public int file() {
        return fileIndex;
    }

    public int rank() {
        return rankIndex;
    }
}
