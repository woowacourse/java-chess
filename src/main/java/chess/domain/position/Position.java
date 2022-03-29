package chess.domain.position;

import chess.domain.Direction;
import java.util.Objects;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Column column;
    private final Rank rank;

    public Position(final Column column, final Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position create(final String position) {
        final Column column = Column.of(position.substring(FILE_INDEX, RANK_INDEX));
        final Rank rank = Rank.of(position.substring(RANK_INDEX));
        return new Position(column, rank);
    }

    public Position move(final Direction direction) {
        return new Position(direction.moveFile(column), direction.moveRank(rank));
    }

    public boolean isSameRank(final Rank rank) {
        return rank == this.rank;
    }

    public boolean isSameColumn(final Column column) {
        return column == this.column;
    }

    public int calculateColumnDistance(final Position from) {
        return this.column.calculateDistance(from.column);
    }

    public int calculateRankDistance(final Position from) {
        return this.rank.calculateDistance(from.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, column);
    }
}
