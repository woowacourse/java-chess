package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Position {

    public static final Map<String, Position> POSITION_CACHE = initPositionCache();

    private final Column column;
    private final Rank rank;

    public Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    private static Map<String, Position> initPositionCache() {
        Map<String, Position> positionCache = new HashMap<>();
        for (Rank rank : Rank.values()) {
            addPositionByEachRank(rank, positionCache);
        }
        return positionCache;
    }

    private static void addPositionByEachRank(Rank rank, Map<String, Position> positionCache) {
        for (Column column : Column.values()) {
            String columnName = column.getName();
            String rankName = rank.getName();
            positionCache.put(columnName + rankName, new Position(Column.findColumn(columnName), Rank.findRank(rankName)));
        }
    }

    public static Position findPosition(String position) {
        validate(position);
        if (POSITION_CACHE.containsKey(position)) {
            return POSITION_CACHE.get(position);
        }
        throw new IllegalArgumentException("[ERROR] 해당 Position은 존재하지 않습니다.");
    }

    private static void validate(String position) {
        String pattern = "[a-h][1-8]";
        if (!Pattern.matches(pattern, position)) {
            throw new IllegalArgumentException("[ERROR] 위치값의 형식이 옳지 않습니다.");
        }
    }

    public Position getMovingPosition(Direction direction) {
        String positionCacheKeyMovedToDirection = direction.findColumnNameMovedToDirection(this.column) + direction.findRankMovedToDirection(this.rank);
        validateBorderOfChessBoard(positionCacheKeyMovedToDirection);
        return POSITION_CACHE.get(positionCacheKeyMovedToDirection);
    }

    public void validateBorderOfChessBoard(String positionCacheKey) {
        if (!POSITION_CACHE.containsKey(positionCacheKey)) {
            throw new IllegalArgumentException("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
        }
    }

    public void validateBorderOfChessBoard(Position position) {
        if (!POSITION_CACHE.containsValue(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
        }
    }

    public int getColumnDistanceFromTargetToSource(Position targetPosition) {
        return targetPosition.getColumnSequence() - column.getSequence();
    }

    public int getRankDistanceFromTargetToSource(Position targetPosition) {
        return targetPosition.getRankSequence() - rank.getSequence();
    }

    public boolean isDiagonalMovement(Position targetPosition) {
        return Math.abs(getColumnDistanceFromTargetToSource(targetPosition)) == Math.abs(getRankDistanceFromTargetToSource(targetPosition));
    }

    public boolean isCrossMovement(Position targetPosition) {
        if (getColumnDistanceFromTargetToSource(targetPosition) == 0 && Math.abs(getRankDistanceFromTargetToSource(targetPosition)) > 0) {
            return true;
        }
        return Math.abs(getColumnDistanceFromTargetToSource(targetPosition)) > 0 && getRankDistanceFromTargetToSource(targetPosition) == 0;
    }

    public int calculateColumnVector(Position targetPosition) {
        if (isDiagonalMovement(targetPosition)) {
            return calculateDiagonalColumnVector(targetPosition);
        }
        if (isCrossMovement(targetPosition)) {
            return calculateCrossColumnVector(targetPosition);
        }
        return getColumnDistanceFromTargetToSource(targetPosition);
    }

    public int calculateRankVector(Position targetPosition) {
        if (isDiagonalMovement(targetPosition)) {
            return calculateDiagonalRankVector(targetPosition);
        }
        if (isCrossMovement(targetPosition)) {
            return calculateCrossRankVector(targetPosition);
        }
        return getRankDistanceFromTargetToSource(targetPosition);
    }

    public int calculateDiagonalColumnVector(Position targetPosition) {
        if (!isDiagonalMovement(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 대각선 방향으로 이동하지 않으므로 Column Vector를 구할 수 없습니다.");
        }
        int columnDistance = getColumnDistanceFromTargetToSource(targetPosition);
        return columnDistance / Math.abs(columnDistance);
    }

    public int calculateDiagonalRankVector(Position targetPosition) {
        if (!isDiagonalMovement(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 대각선 방향으로 이동하지 않으므로 Rank Vector를 구할 수 없습니다.");
        }
        int rankDistance = getRankDistanceFromTargetToSource(targetPosition);
        return rankDistance / Math.abs(rankDistance);

    }

    public int calculateCrossColumnVector(Position targetPosition) {
        if (!isCrossMovement(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 십자 방향으로 이동하지 않으므로 Column Vector를 구할 수 없습니다.");
        }
        int columnDistance = getColumnDistanceFromTargetToSource(targetPosition);
        if (columnDistance == 0) {
            return columnDistance;
        }
        return columnDistance / Math.abs(columnDistance);
    }

    public int calculateCrossRankVector(Position targetPosition) {
        if (!isCrossMovement(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 십자 방향으로 이동하지 않으므로 Rank Vector를 구할 수 없습니다.");
        }
        int rankDistance = getRankDistanceFromTargetToSource(targetPosition);
        if (rankDistance == 0) {
            return rankDistance;
        }
        return rankDistance / Math.abs(rankDistance);
    }

    public char getColumnSequence() {
        return column.getSequence();
    }

    public int getRankSequence() {
        return rank.getSequence();
    }

    public Column getColumn() {
        return column;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", rank=" + rank +
                '}';
    }
}
