package chess.domain.position;

import chess.domain.piece.notation.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private static final int NEXT = 1;

    private final int fileGrowth;
    private final int rankGrowth;

    Direction(final int fileGrowth, final int rankGrowth) {
        this.fileGrowth = fileGrowth;
        this.rankGrowth = rankGrowth;
    }

    public static Direction of(final Position from, final Position to) {
        final var fileDifference = to.getFileOrder() - from.getFileOrder();
        final var rankDifference = to.getRankNumber() - from.getRankNumber();

        return Arrays.stream(values())
                .filter(direction -> direction.fileGrowth == fileDifference && direction.rankGrowth == rankDifference)
                .findFirst()
                .orElseGet(() -> getGrowthDirection(from, to));
    }

    private static Direction getGrowthDirection(final Position from, final Position to) {
        final var fileDifference = calculateDifference(from.getFileOrder(), to.getFileOrder());
        final var rankDifference = calculateDifference(from.getRankNumber(), to.getRankNumber());

        return Arrays.stream(values())
                .filter(direction -> direction.fileGrowth == fileDifference && direction.rankGrowth == rankDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("정의되지 않은 방향입니다."));
    }

    private static int calculateDifference(final int from, final int to) {
        final var difference = to - from;
        if (difference == 0) {
            return difference;
        }
        return difference / Math.abs(difference);
    }

    public static List<Direction> PawnDirection(final Color color) {
        if (color.isWhite()) {
            return whitePawnDirection();
        }
        return blackPawnDirection();
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    private static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    private static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public List<Position> getPositions(final Position start, final Position end) {
        final var rankDifference = Math.abs(start.getRankNumber() - end.getRankNumber());
        final var fileDifference = Math.abs(start.getFileOrder() - end.getFileOrder());
        final var endCount = Math.max(rankDifference, fileDifference);
        var file = start.getFileOrder() + fileGrowth;
        var rank = start.getRankNumber() + rankGrowth;
        final List<Position> positions = new ArrayList<>();
        for (int i = NEXT; i < endCount; i++, file += fileGrowth, rank += rankGrowth) {
            positions.add(Position.of(File.findByOrder(file), Rank.from(rank)));
        }
        return positions;
    }

    public boolean isVertical() {
        return this == Direction.NORTH || this == Direction.SOUTH;
    }

    public boolean isDiagonal() {
        return this == Direction.NORTHEAST || this == Direction.SOUTHEAST || this == Direction.SOUTHWEST || this == Direction.NORTHWEST;
    }
}
