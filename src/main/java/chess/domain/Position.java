
package chess.domain;

import chess.practiceMove.Direction;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final Column column;

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
