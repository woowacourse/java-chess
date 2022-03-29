package chess.model.position;

import chess.model.direction.route.Route;
import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final Rank rank, final File file) {
        return new Position(rank, file);
    }

    public static Position from(final String position) {
        return new Position(Rank.of(position.charAt(1)), File.of(position.charAt(0)));
    }

    public int subtractRankFrom(final Position otherPosition) {
        return rank.subtractFrom(otherPosition.rank);
    }

    public int subtractFileFrom(final Position otherPosition) {
        return file.subtractFrom(otherPosition.file);
    }

    public Position createPositionTo(final Route route) {
        return route.createPositionFrom(rank, file);
    }

    public boolean isNotInitialPawnPosition() {
        return !rank.equals(Rank.TWO) && !rank.equals(Rank.SEVEN);
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
