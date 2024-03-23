package domain.board;

import java.util.Arrays;

public enum Direction {

    UP(0, 1),
    UPRIGHT(1, 1),
    RIGHT(1, 0),
    DOWNRIGHT(1, -1),
    DOWN(0, -1),
    DOWNLEFT(-1, -1),
    LEFT(-1, 0),
    UPLEFT(-1, 1);

    private final int fileDirection;
    private final int rankDirection;

    Direction(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static Direction of(Position source, Position target) {
        int fileDifference = target.calculateFileDifference(source);
        int rankDifference = target.calculateRankDifference(source);

        return Arrays.stream(values())
                .filter(direction -> direction.fileDirection == decideFileDirection(fileDifference)
                        && direction.rankDirection == decideRankDirection(rankDifference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    public static int decideFileDirection(int fileDifference) {
        if (fileDifference > 0) {
            return 1;
        }
        if (fileDifference < 0) {
            return -1;
        }
        return 0;
    }

    public static int decideRankDirection(int rankDifference) {
        if (rankDifference > 0) {
            return 1;
        }
        if (rankDifference < 0) {
            return -1;
        }
        return 0;
    }

    public int getFileDirection() {
        return fileDirection;
    }

    public int getRankDirection() {
        return rankDirection;
    }
}
