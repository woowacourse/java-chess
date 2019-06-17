package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Row> rows;

    public Board(List<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }
}
