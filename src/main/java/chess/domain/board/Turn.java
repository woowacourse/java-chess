package chess.domain.board;

import chess.domain.piece.Color;

import java.util.Objects;

public class Turn {

    private final Color color;

    public Turn(final Color color) {
        this.color = color;
    }

    public Turn change() {
        return new Turn(color.opposite());
    }

    public Color color() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Turn turn = (Turn) o;
        return color == turn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
