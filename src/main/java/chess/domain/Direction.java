package chess.domain;

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

    public static boolean isMovableToEast(Position sourcePosition, Position targetPosition) {
        char sourceColumn = sourcePosition.getColumnSequence();
        int sourceRank = sourcePosition.getRankSequence();
        char targetColumn = targetPosition.getColumnSequence();
        int targetRank = targetPosition.getRankSequence();

        return sourceColumn < targetColumn && sourceRank == targetRank;
    }

    public static boolean isMovableToWest(Position sourcePosition, Position targetPosition) {
        char sourceColumn = sourcePosition.getColumnSequence();
        int sourceRank = sourcePosition.getRankSequence();
        char targetColumn = targetPosition.getColumnSequence();
        int targetRank = targetPosition.getRankSequence();

        return sourceColumn > targetColumn && sourceRank == targetRank;
    }

    public static boolean isMovableToSouth(Position sourcePosition, Position targetPosition) {
        char sourceColumn = sourcePosition.getColumnSequence();
        int sourceRank = sourcePosition.getRankSequence();
        char targetColumn = targetPosition.getColumnSequence();
        int targetRank = targetPosition.getRankSequence();

        return sourceColumn == targetColumn && sourceRank > targetRank;
    }

    public static boolean isMovableNorth(Position sourcePosition, Position targetPosition) {
        char sourceColumn = sourcePosition.getColumnSequence();
        int sourceRank = sourcePosition.getRankSequence();
        char targetColumn = targetPosition.getColumnSequence();
        int targetRank = targetPosition.getRankSequence();

        return sourceColumn == targetColumn && sourceRank < targetRank;
    }

    public static boolean isMovableNorthEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource > 0 && rankDistanceFromTargetToSource > 0 && columnDistanceFromTargetToSource == rankDistanceFromTargetToSource;
    }

    public static boolean isMovableNorthWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource < 0 && rankDistanceFromTargetToSource > 0 && -columnDistanceFromTargetToSource == rankDistanceFromTargetToSource;
    }

    public static boolean isMovableSouthEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource > 0 && rankDistanceFromTargetToSource < 0 && columnDistanceFromTargetToSource == -rankDistanceFromTargetToSource;
    }

    public static boolean isMovableSouthWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource < 0 && rankDistanceFromTargetToSource < 0 && columnDistanceFromTargetToSource == rankDistanceFromTargetToSource;
    }

    public static boolean isMovableNorthNorthEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == NORTH_NORTH_EAST.columnVector && rankDistanceFromTargetToSource == NORTH_NORTH_EAST.rankVector;
    }

    public static boolean isMovableNorthNorthWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == NORTH_NORTH_WEST.columnVector && rankDistanceFromTargetToSource == NORTH_NORTH_WEST.rankVector;
    }

    public static boolean isMovableNorthEastEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == NORTH_EAST_EAST.columnVector && rankDistanceFromTargetToSource == NORTH_EAST_EAST.rankVector;
    }

    public static boolean isMovableNorthWestWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == NORTH_WEST_WEST.columnVector && rankDistanceFromTargetToSource == NORTH_WEST_WEST.rankVector;
    }

    public static boolean isMovableSouthSouthEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == SOUTH_SOUTH_EAST.columnVector && rankDistanceFromTargetToSource == SOUTH_SOUTH_EAST.rankVector;
    }

    public static boolean isMovableSouthSouthWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == SOUTH_SOUTH_WEST.columnVector && rankDistanceFromTargetToSource == SOUTH_SOUTH_WEST.rankVector;
    }

    public static boolean isMovableSouthEastEast(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == SOUTH_EAST_EAST.columnVector && rankDistanceFromTargetToSource == SOUTH_EAST_EAST.rankVector;

    }

    public static boolean isMovableSouthWestWest(Position sourcePosition, Position targetPosition) {
        int columnDistanceFromTargetToSource = sourcePosition.getColumnDistanceFromTargetToSource(targetPosition);
        int rankDistanceFromTargetToSource = sourcePosition.getRankDistanceFromTargetToSource(targetPosition);
        return columnDistanceFromTargetToSource == SOUTH_WEST_WEST.columnVector && rankDistanceFromTargetToSource == SOUTH_WEST_WEST.rankVector;
    }

    public static boolean isRookDirection(Direction direction) {
        return direction.equals(EAST) || direction.equals(WEST) || direction.equals(SOUTH) || direction.equals(NORTH);
    }


    public int getColumnVector() {
        return columnVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
