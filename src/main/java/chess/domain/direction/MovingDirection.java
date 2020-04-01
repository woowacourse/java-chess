package chess.domain.direction;

import chess.domain.position.Position;
import chess.exception.MovingException;

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

    private static final int SAME_DIRECTION_THRESHOLD = 0;

    private final int fileDirection;
    private final int rankDirection;

    MovingDirection(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static MovingDirection getDirection(Position source, Position target) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(source, target))
                .findFirst()
                .orElseThrow(MovingException::new);
    }

    private boolean isSameDirection(Position source, Position target) {
        double fileDifference = source.getFileDifference(target);
        double rankDifference = source.getRankDifference(target);
        Double tangent = rankDifference / fileDifference;
        return isSameTangent(tangent) && isSameFileDirection(fileDifference) && isSameRankDirection(rankDifference);
    }

    private boolean isSameTangent(Double tangent) {
        Double directionTangent = this.rankDirection / (double) this.fileDirection;
        return directionTangent.equals(tangent);
    }

    private boolean isSameFileDirection(double fileDifference) {
        return fileDirection * fileDifference >= SAME_DIRECTION_THRESHOLD;
    }

    private boolean isSameRankDirection(double rankDifference) {
        return rankDirection * rankDifference >= SAME_DIRECTION_THRESHOLD;
    }

    public int getFileDirection() {
        return fileDirection;
    }

    public int getRankDirection() {
        return rankDirection;
    }
}
