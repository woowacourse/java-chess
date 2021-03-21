package chess.domain.board.position;

import chess.domain.piece.rule.Direction;
import chess.domain.piece.rule.Distance;

import java.util.Objects;

// XXX :: Position 캐싱하기

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

    public static Position of(final String input) {
        if (input.split("").length != 2) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }

        return new Position(input);
    }

    public Horizontal getHorizontal() {
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
}
