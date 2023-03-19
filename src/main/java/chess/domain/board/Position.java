
package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    private final Rank rank;
    private final Column column;
    private static final List<Position> cache;

    /** todo 4: 질문4
     * Position에서 사용하는 값은 정해져 있어서 캐싱을 이용했는데요,
     * stream filter을 이용하면, 객체를 생성할때마다 모든 List<Position> cache를 순회하는데,
     * 성능적으로 이게 맞는 캐싱인지 의문이 듭니다.
     *
     * 이에 대한 리뷰어님의 의견이 궁금합니다!
     */
    static {
        cache = Column.getOrderedColumns().stream()
                .flatMap(column -> Rank.getOrderedRanks().stream()
                        .map(rank -> new Position(column,rank)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position of(Column column, Rank rank) {
        return cache.stream()
                .filter(position -> position.column == column && position.rank == rank)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 Position입니다"));
    }


    public int findGapOfRank(Position destination) {
        return destination.rank.getIndex() - rank.getIndex();
    }

    public int findGapOfColumn(Position destination) {
        return destination.column.getIndex() - column.getIndex();
    }

    public Position moveDirection(Direction direction) {
        Column newColumn = Column.findColumnByIndex(column.getIndex() + direction.getX());
        Rank newRank = Rank.findRankByIndex(rank.getIndex() + direction.getY());

        return new Position(newColumn, newRank);
    }

    @Override
    public String toString() {
        return column.name() + rank.name();
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
}
