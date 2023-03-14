package chess.domain;

import java.util.List;
import java.util.Objects;

public class Board {

    private final List<Row> rows;

    public Board(final List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Board board = (Board) o;
        return Objects.equals(rows, board.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
