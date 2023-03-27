package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    public static final Map<String, Position> POSITION_CACHE = new HashMap<>();
    public static final int WHITE_PAWN_INIT_RANK = 2;
    public static final int BLACK_PAWN_INIT_RANK = 7;

    static {
        for (Rank rank : Rank.values()) {
            for (Column column : Column.values()) {
                String columnName = column.getName();
                String rankName = rank.getName();
                POSITION_CACHE.put(columnName + rankName, new Position(Column.findColumn(columnName), Rank.findRank(rankName)));
            }
        }
    }

    private final Column column;
    private final Rank rank;


    private Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position findPosition(String position) {
        if (POSITION_CACHE.containsKey(position)) {
            return POSITION_CACHE.get(position);
        }
        throw new IllegalArgumentException("[ERROR] 해당 Position은 존재하지 않습니다.");
    }

    public Position getMovingPosition(Direction direction) {
        char columSequence = column.getSequence();
        int rankSequence = rank.getSequence();
        int ColumnVector = direction.getColumnVector();
        int RankVector = direction.getRankVector();
        char a = (char) (columSequence + ColumnVector);
        int b = rankSequence + RankVector;
        String positionCacheKey = a + String.valueOf(b);
        validateBorderOfChessBoard(positionCacheKey);
        return POSITION_CACHE.get(positionCacheKey);
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

    public boolean isInWhitePawnInitRank() {
        return rank.getSequence() == WHITE_PAWN_INIT_RANK;
    }

    public boolean isInBlackPawnInitRank() {
        return rank.getSequence() == BLACK_PAWN_INIT_RANK;
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
