package chess.domain;

import chess.domain.player.Team;

import java.util.Arrays;

public enum Direction {

    STOP(0, 0),

    N(1, 0),
    NE(1, 1),
    E(0, 1),
    SE(-1, 1),
    S(-1, 0),
    SW(-1, -1),
    W(0, -1),
    NW(1, -1),

    NNE(2, 1),
    NEE(1, 2),
    SEE(-1, 2),
    SSE(-2, 1),
    SSW(-2, -1),
    SWW(-1, -2),
    NWW(1, -2),
    NNW(2, -1);

    private final int rank;
    private final int file;

    Direction(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Direction of(final Position current, final Position destination) {
        final int rankDistance = current.findRankDistance(destination);
        final int fileDistance = current.findFileDistance(destination);
        if (rankDistance == 0 && fileDistance == 0) {
            return STOP;
        }
        return findDirection(rankDistance, fileDistance);
    }

    private static Direction findDirection(int rankDistance, int fileDistance) {
        final int greatestCommonDivisor = findGreatestCommonDivisor(Math.abs(rankDistance), Math.abs(fileDistance));
        return Arrays.stream(Direction.values())
                .filter(value ->
                        isSameDirection(rankDistance / greatestCommonDivisor,
                                fileDistance / greatestCommonDivisor, value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    private static int findGreatestCommonDivisor(final int bigNumber, final int smallNumber) {
        if (bigNumber < smallNumber) {
            return findGreatestCommonDivisor(smallNumber, bigNumber);
        }
        if (smallNumber == 0) {
            return bigNumber;
        }
        return findGreatestCommonDivisor(smallNumber, bigNumber % smallNumber);
    }

    private static boolean isSameDirection(final int rankDirection, final int fileDirection,
                                           final Direction direction) {
        return rankDirection == direction.rank && fileDirection == direction.file;
    }

    public boolean isMoveLinear() {
        return this == N || this == E || this == S || this == W;
    }

    public boolean isMoveDiagonal() {
        return this == NE || this == SE || this == SW || this == NW;
    }

    public boolean isMoveOfKnight() {
        return this == NNE || this == NEE || this == SEE || this == SSE ||
                this == SSW || this == SWW || this == NWW || this == NNW;
    }

    public boolean isMoveForward(final Team team) {
        if (team == Team.BLACK) {
            return this == S;
        }
        return this == N;
    }

    public boolean isMoveDiagonalForward(final Team team) {
        if (team == Team.BLACK) {
            return this == SE || this == SW;
        }
        return this == NE || this == NW;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
