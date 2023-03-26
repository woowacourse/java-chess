
package chess.domain.board;

import chess.domain.piece.CrossDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    private final Rank rank;
    private final Column column;
    private static final List<Position> cache;

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


    public List<Position> findRouteTo(Position end) {
        int gapOfColumn = this.findGapOfColumn(end);
        int gapOfRank = this.findGapOfRank(end);
        if(CrossDirection.isCrossDirection(gapOfColumn, gapOfRank)) {
            CrossDirection direction = CrossDirection.findDirectionByGap(gapOfColumn,gapOfRank);
            return createCrossRoute(direction, end);
        }
        return Collections.emptyList();
    }

    private List<Position> createCrossRoute(CrossDirection direction, Position end) {
        List<Position> route = new ArrayList<>();
        Position currentPosition = this;
        do {
            currentPosition = currentPosition.moveByUnitVector(direction);
            route.add(currentPosition);
        } while (!currentPosition.equals(end));
        return route;
    }

    private Position moveByUnitVector(CrossDirection direction) {
        Column newColumn = Column.findColumnByIndex(column.getIndex() + direction.getGapOfColumn());
        Rank newRank = Rank.findRankByIndex(rank.getIndex() + direction.getGapOfRank());

        return new Position(newColumn, newRank);
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

    public Rank getRank() {
        return rank;
    }

    public Column getColumn() {
        return column;
    }
}
