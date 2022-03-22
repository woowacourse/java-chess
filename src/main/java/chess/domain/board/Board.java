package chess.domain.board;

import static chess.domain.square.Color.BLACK;
import static chess.domain.square.Color.WHITE;

import java.util.List;

public class Board {

    private final List<Row> rows;

    public Board() {
        this.rows = List.of(
                Row.ofMainPieces(BLACK),
                Row.ofPawn(BLACK),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofEmpty(),
                Row.ofPawn(WHITE),
                Row.ofMainPieces(WHITE));
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Board{" + "rows=" + rows + '}';
    }
}
