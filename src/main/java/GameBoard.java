import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final List<List<String>> board;

    public GameBoard() {
        this.board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(List.of(".", ".", ".", ".", ".", ".", ".", "."));
        }
    }

    public List<List<String>> getBoard() {
        return board;
    }
}
