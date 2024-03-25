package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.piece.blank.Blank;
import java.util.HashMap;
import java.util.Map;

public class BlankBoard {
    private final Map<Position, Piece> board = new HashMap<>();

    public BlankBoard() {
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
            board.put(position, new Blank());
        }
    }

    public Map<Position, Piece> fillWith(Map<Position, Piece> want) {
        for (Position position : want.keySet()) {
            board.replace(position, want.get(position));
        }
        return board;
    }
}
