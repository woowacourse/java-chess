package chess.domain.position;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;

import java.util.Objects;

import chess.domain.move.Direction;
import chess.domain.move.Directions;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position move(Direction direction) {
        File newFile = file.move(direction);
        Rank newRank = rank.move(direction);
        return new Position(newFile, newRank);
    }

    public Directions getDirectionsTo(Position other) {
        Directions horizontalDirections = getHorizontalDirectionsTo(other);
        Directions verticalDirections = getVerticalDirectionsTo(other);
        return horizontalDirections.join(verticalDirections);
    }

    private Directions getHorizontalDirectionsTo(Position other) {
        int difference = other.file.minus(file);
        if (difference < 0) {
            return new Directions(LEFT.repeat(Math.abs(difference)));
        }
        return new Directions(RIGHT.repeat(difference));
    }

    private Directions getVerticalDirectionsTo(Position other) {
        int difference = other.rank.minus(rank);
        if (difference > 0) {
            return new Directions(UP.repeat(difference));
        }
        return new Directions(DOWN.repeat(Math.abs(difference)));
    }

    public int getFileIndex() {
        return file.getIndex();
    }

    public int getRankIndex() {
        return rank.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position)o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
