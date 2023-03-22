package chess.domain.board;

import chess.exception.PieceCanNotMoveException;
import java.math.BigInteger;
import java.util.Arrays;

public enum Direction {
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_UP_LEFT(-1, 2),
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction calculateDirection(Square source, Square target) {
        int fileGap = source.calculateFileGap(target);
        int rankGap = source.calculateRankGap(target);

        int gcd = calculateGcd(fileGap, rankGap);

        int fileDirection = fileGap / gcd;
        int rankDirection = rankGap / gcd;

        return findDirection(fileDirection, rankDirection);
    }

    private static int calculateGcd(int fileGap, int rankGap) {
        BigInteger b1 = BigInteger.valueOf(fileGap);
        BigInteger b2 = BigInteger.valueOf(rankGap);

        return b1.gcd(b2).intValue();
    }

    private static Direction findDirection(int fileDirection, int rankDirection) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.file == fileDirection && direction.rank == rankDirection)
                .findFirst()
                .orElseThrow(PieceCanNotMoveException::new);
    }

    public static boolean isMoveForward(Direction direction) {
        return direction == Direction.UP || direction == Direction.DOWN;
    }

    public static boolean isMoveDiagonal(Direction direction) {
        return direction == Direction.RIGHT_UP || direction == Direction.RIGHT_DOWN
                || direction == Direction.LEFT_UP || direction == Direction.LEFT_DOWN;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
