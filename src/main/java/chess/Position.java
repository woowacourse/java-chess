package chess;

import chess.direction.Route;
import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(Rank rank, File file) {
        return new Position(rank, file);
    }

    public static Position from(String position) {
        return new Position(Rank.of(position.charAt(1)), File.of(position.charAt(0)));
    }

    public boolean isInBoardAfterMoved(Direction direction) {
        return direction.isMovablePosition(rank, file);
    }

    public Position createMovablePosition(Direction direction) {
        return direction.createMovablePosition(rank, file);
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

    public boolean isStart() {
        return rank.equals(Rank.TWO) || rank.equals(Rank.SEVEN);
    }

    public int subtractRankFrom(final Position otherPosition) {
        return rank.subtractFrom(otherPosition.rank);
    }

    public int subtractFileFrom(final Position otherPosition) {
        return file.subtractFrom(otherPosition.file);
    }

    public Position createPositionFrom(Route route) {
        return route.createPositionFrom(rank, file);
    }

    public boolean canCreatePositionTo(Route route) {
        return route.canCreatePosition(rank, file);
    }
}
