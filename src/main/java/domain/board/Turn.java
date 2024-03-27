package domain.board;

import domain.piece.Color;

import java.util.Objects;

public class Turn {

    private final Color color;

    public Turn(Color color) {
        this.color = color;
    }

    public Turn swap() {
        return new Turn(color.opposite());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return color == turn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
