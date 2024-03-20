package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class TestBoardFactory {
    private final Map<Position, Piece> board = new HashMap<>();

    public TestBoardFactory() {
        createBlankBoard();
    }

    private void createBlankBoard() {
        for (int x = 1; x <= 8; x++) {
            initLine(x);
        }
    }

    private void initLine(int x) {
        for (int y = 1; y <= 8; y++) {
            Position position = new Position(x, y);
            board.put(position, new Blank(position));
        }
    }

    public Map<Position, Piece> getTestBoard(Map<Position, Piece> want) {
        for (Position position : want.keySet()) {
            board.replace(position, want.get(position));
        }
        return board;
    }
}
