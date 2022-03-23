import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        return board;
    }


    private void createInitialize(Map<Position, Piece> board) {
        for (Row row : Row.values()) {
            initializeByRow(board, row);
        }
    }

    private void initializeByRow(Map<Position, Piece> board, Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(row, column), null);
        }
    }
}
