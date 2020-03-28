package chess.domain.direction;

import chess.domain.position.Position;
import chess.exception.MovingDirectionException;

import java.util.Arrays;

public enum MovingDirection {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    NORTH_NORTH_EAST(1, 2),
    NORTH_EAST_EAST(2, 1),
    SOUTH_EAST_EAST(2, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    SOUTH_WEST_WEST(-2, -1),
    NORTH_WEST_WEST(-2, 1),
    NORTH_NORTH_WEST(-1, 2);

    private final int fileDirection;
    private final int rankDirection;

    MovingDirection(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static MovingDirection getDirection(Position source, Position target) {
        double fileDifference = source.getFileDifference(target);
        double rankDifference = source.getRankDifference(target);

        Double tangent = rankDifference / fileDifference;

        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(tangent))
                .filter(direction -> direction.isSameFileDirection(fileDifference))
                .filter(direction -> direction.isSameRankDirection(rankDifference))
                .findFirst()
                .orElseThrow(() -> new MovingDirectionException());
    }

    private boolean isSameDirection(Double tangent) {
        Double directionTangent = this.rankDirection / (double) this.fileDirection;
        return directionTangent.equals(tangent);
    }

    private boolean isSameFileDirection(double fileDifference) {
        return fileDirection * fileDifference >= 0;
    }

    private boolean isSameRankDirection(double rankDifference) {
        return rankDirection * rankDifference >= 0;
    }

    public int getFileDirection() {
        return fileDirection;
    }

    public int getRankDirection() {
        return rankDirection;
    }
}
