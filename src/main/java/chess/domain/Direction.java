package chess.domain;

import java.util.Arrays;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    NORTH_EAST_EAST(2, 1),
    NORTH_WEST_WEST(-2, 1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    SOUTH_EAST_EAST(2, -1),
    SOUTH_WEST_WEST(-2, -1);

    private final int columnVector;

    private final int rankVector;

    Direction(int columnVector, int rankVector) {
        this.columnVector = columnVector;
        this.rankVector = rankVector;
    }

    public static Direction findDirectionFromSourceToTarget(Position sourcePosition, Position targetPosition) {
        int columnVector = sourcePosition.calculateColumnVector(targetPosition);
        int rankVector = sourcePosition.calculateRankVector(targetPosition);

        return Arrays.stream(values())
                .filter(direction -> direction.columnVector == columnVector && direction.rankVector == rankVector)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 이동 가능한 방향이 존재하지 않습니다."));
    }

    public String findColumnNameMovedToDirection(Column column) {
        return Column.findColumnName((char) (column.getSequence() + this.columnVector));
    }

    public String findRankMovedToDirection(Rank rank) {
        return Rank.findRankName(rank.getSequence() + this.rankVector);
    }
}
