package chess.domain;

import java.util.List;
import java.util.Objects;

public class Row {
    private final List<Square> squares;

    public Row(final List<Square> squares) {
        this.squares = squares;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Row row = (Row) o;
        return Objects.equals(squares, row.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
