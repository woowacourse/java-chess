package chess.domain;

import chess.direction.Direction;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final Column column;

    public Position(String column, String rank) {
        this(Column.findColumnByValue(column), Rank.findRankByValue(rank));
    }

    public Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public int findGapOfRank(Position destination) {
        return destination.rank.getIndex() - rank.getIndex();
    }

    public int findGapOfColum(Position destination) {
        return destination.column.getIndex() - column.getIndex();
    }

    public Position moveDirection(Direction direction) {
        Column columnOfDestination = Column.findColumnByIndex(column.getIndex() + direction.getX());
        Rank rankOfDestination = Rank.findRankByIndex(rank.getIndex() + direction.getY());

        return new Position(columnOfDestination, rankOfDestination);
    }

    @Override
    public String toString() {
        return column.name() + rank.name();
    }

    public Column getColumn() {
        return column;
    }

    public int getColumnIndex() {
        return column.getIndex();
    }

    public boolean isSamePosition(Position position){
        return this.equals(position);
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
