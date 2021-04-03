package chess.domain.board.position;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.Objects;

public class Position {
    private final Vertical vertical;
    private final Horizontal horizontal;

    public Position(final Vertical vertical, final Horizontal horizontal) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position(final String[] vh) {
        this(Vertical.parse(vh[0]), Horizontal.parse(vh[1]));
    }

    public Position(final String s) {
        this(s.split(""));
    }

    public Vertical vertical() {
        return vertical;
    }

    public Horizontal horizontal() {
        return horizontal;
    }

    public Position next(final Direction direction, final Distance distance) {
        return new Position(vertical.add(direction, distance), horizontal.add(direction, distance));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }

    public String parseAsString() {
        return vertical().name() + horizontal().getIndex();
    }
}
