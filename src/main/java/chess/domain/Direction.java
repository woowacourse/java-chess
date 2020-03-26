package chess.domain;

import chess.domain.position.Position;

import java.util.Arrays;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);
//    KNIGHT_RIGHT_UP(2,1),
//    KNIGHT_RIGHT_DOWN(2,-1),
//    KNIGHT_UP_RIGHT(1,2),
//    KNIGHT_UP_LEFT(-1,2),
//    KNIGHT_LEFT_UP(-2,1),
//    KNIGHT_LEFT_DOWN(-2,-1),
//    KNIGHT_DOWN_LEFT(-1,-2),
//    KNIGHT_DOWN_RIGHT(1,-2),

    private final int fileDirection;
    private final int rankDirection;

    Direction(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static Direction getDirection(Position source, Position target) {
        int fileDirection = getDirectionElement(source.getFileDifference(target));
        int rankDirection = getDirectionElement(source.getRankDifference(target));

        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(fileDirection, rankDirection))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옳지 않은 방향입니다."));
    }

    private static int getDirectionElement(int difference) {
        int localFileDirection;
        try {
            localFileDirection = difference / Math.abs(difference);
        } catch (ArithmeticException ae) {
            localFileDirection = 0;
        }
        return localFileDirection;
    }

    public boolean isSameDirection(int fileDirection, int rankDirection) {
        return this.fileDirection == fileDirection && this.rankDirection == rankDirection;
    }

    public int getFileDirection() {
        return fileDirection;
    }

    public int getRankDirection() {
        return rankDirection;
    }
}
