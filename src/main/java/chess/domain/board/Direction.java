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
    EMPTY(0, 0);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction calculateDirection(final Square source, final Square target) {
        final int fileGap = source.calculateFileGap(target);
        final int rankGap = source.calculateRankGap(target);

        final int gcd = calculateGcd(fileGap, rankGap);

        final int fileDirection = fileGap / gcd;
        final int rankDirection = rankGap / gcd;

        return findDirection(fileDirection, rankDirection);
    }

    private static int calculateGcd(final int fileGap, final int rankGap) {
        final BigInteger b1 = BigInteger.valueOf(fileGap);
        final BigInteger b2 = BigInteger.valueOf(rankGap);

        return b1.gcd(b2).intValue();
    }

    private static Direction findDirection(final int fileDirection, final int rankDirection) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.file == fileDirection && direction.rank == rankDirection)
                .findFirst()
                .orElseThrow(PieceCanNotMoveException::new);
    }

    public static boolean isMoveForward(final Direction direction) {
        return direction == Direction.UP || direction == Direction.DOWN;
    }

    public static boolean isMoveDiagonal(final Direction direction) {
        return direction == Direction.RIGHT_UP || direction == Direction.RIGHT_DOWN
                || direction == Direction.LEFT_UP
                || direction == Direction.LEFT_DOWN;
    }

    public int getFile() {
        return this.file;
    }

    public int getRank() {
        return this.rank;
    }
}
