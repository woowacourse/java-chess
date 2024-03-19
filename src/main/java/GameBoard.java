import java.util.ArrayList;
import java.util.List;
import point.Column;
import point.Point;
import point.Row;

public class GameBoard {

    private final List<List<Square>> board;

    public GameBoard() {
        this.board = init();
    }

    private List<List<Square>> init() {
        List<List<Square>> board = new ArrayList<>();
        for (Column column : Column.values()) {
            List<Square> line = new ArrayList<>();
            for (Row row : Row.values()) {
                line.add(new Square(new Point(row, column), new SquareInfo(BoardInfo.BLANK, Camp.GRAY)));
            }
            board.add(line);
        }
        return board;
    }

    public List<List<Square>> getBoard() {
        return board;
    }
}
