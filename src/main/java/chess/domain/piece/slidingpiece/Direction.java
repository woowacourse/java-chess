package chess.domain.piece.slidingpiece;

import chess.domain.position.Position;
import java.util.Arrays;

public enum Direction {

    UP(0, 1),
    UP_RIGHT(1, 1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, -1),
    DOWN(0, -1),
    DOWN_LEFT(-1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1),
    ;

    private final int fileDirection;
    private final int rankDirection;

    Direction(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static Direction of(Position sourcePosition, Position targetPosition) {
        int fileDifference = targetPosition.calculateFileDifference(sourcePosition);
        int rankDifference = targetPosition.calculateRankDifference(sourcePosition);

        return Arrays.stream(values())
            .filter(direction -> direction.fileDirection == Integer.compare(fileDifference, 0)
                && direction.rankDirection == Integer.compare(rankDifference, 0))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    public int fileDirection() {
        return fileDirection;
    }

    public int rankDirection() {
        return rankDirection;
    }
}
