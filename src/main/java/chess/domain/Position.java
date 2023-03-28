package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    public static final Map<String, Position> POSITION_CACHE = initPositionCache();

    private final Column column;
    private final Rank rank;

    private Position(Column column, Rank rank) {
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
        if (POSITION_CACHE.containsKey(position)) {
            return POSITION_CACHE.get(position);
        }
        throw new IllegalArgumentException("[ERROR] 해당 Position은 존재하지 않습니다.");
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

    public char getColumnSequence() {
        return column.getSequence();
    }

    public int getRankSequence() {
        return rank.getSequence();
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
