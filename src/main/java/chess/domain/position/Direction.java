package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    private final int fileDirection;

    private final int rankDirection;

    Direction(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static Direction findDirection(Position source, Position target) {
        int fileDirection = source.findFileDirection(target);
        int rankDirection = source.findRankDirection(target);

        return Arrays.stream(values())
                .filter(value -> isSameDirection(value, fileDirection, rankDirection))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 방향입니다."));
    }

    private static boolean isSameDirection(Direction value, int fileDirection, int rankDirection) {
        return value.fileDirection == fileDirection && value.rankDirection == rankDirection;
    }

    public static boolean isUpDown(Position source, Position target) {
        Direction direction = findDirection(source, target);
        return direction == UP || direction == DOWN;
    }

    public int getFileDirection() {
        return fileDirection;
    }

    public int getRankDirection() {
        return rankDirection;
    }
}
