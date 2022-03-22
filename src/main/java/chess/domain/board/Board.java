package chess.domain.board;

import chess.domain.square.Color;
import java.util.List;

public class Board {

    private final List<Row> rows;

    public Board() {
        this.rows = List.of(Row.ofEmpty(),
                Row.ofPawn(Color.BLACK),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofPawn(Color.WHITE),
                Row.ofEmpty());
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Board{" + "rows=" + rows + '}';
    }
}
