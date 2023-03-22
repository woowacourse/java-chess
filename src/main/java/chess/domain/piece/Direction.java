package chess.domain.piece;

import chess.domain.square.Square;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    EAST(1, 0),
    NORTH(0, 1),
    WEST(-1, 0),
    SOUTH(0, -1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    NORTH_NORTH_EAST(1, 2),
    NORTH_EAST_EAST(2, 1),
    SOUTH_EAST_EAST(2, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    SOUTH_WEST_WEST(-2, -1),
    NORTH_WEST_WEST(-2, 1),
    NORTH_NORTH_WEST(-1, 2);

    private static final List<Direction> STRAIGHT_DIRECTIONS = List.of(NORTH, WEST, SOUTH, EAST);
    private static final List<Direction> DIAGONAL_DIRECTIONS = List.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    private static final List<Direction> KNIGHT_DIRECTIONS = List.of(
            NORTH_NORTH_EAST, NORTH_EAST_EAST, NORTH_NORTH_WEST, NORTH_WEST_WEST,
            SOUTH_SOUTH_EAST, SOUTH_EAST_EAST, SOUTH_SOUTH_WEST, SOUTH_WEST_WEST);

    private final int fileUnit;
    private final int rankUnit;

    Direction(final int fileUnit, final int rankUnit) {
        this.fileUnit = fileUnit;
        this.rankUnit = rankUnit;
    }

    public static Direction of(final Square sourceSquare, final Square targetSquare) {
        int fileDifference = sourceSquare.calculateFileDifference(targetSquare);
        int rankDifference = sourceSquare.calculateRankDifference(targetSquare);

        int gcd = BigInteger.valueOf(fileDifference)
                .gcd(BigInteger.valueOf(rankDifference))
                .intValue();

        int fileDirection = fileDifference / gcd;
        int rankDirection = rankDifference / gcd;

        return Arrays.stream(values())
                .filter(direction -> direction.fileUnit == fileDirection && direction.rankUnit == rankDirection)
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
                });
    }

    public boolean isDiagonal() {
        return DIAGONAL_DIRECTIONS.contains(this);
    }

    public boolean isStraight() {
        return STRAIGHT_DIRECTIONS.contains(this);
    }

    public boolean isKnightDirection() {
        return KNIGHT_DIRECTIONS.contains(this);
    }

    public int getFileUnit() {
        return fileUnit;
    }

    public int getRankUnit() {
        return rankUnit;
    }
}
