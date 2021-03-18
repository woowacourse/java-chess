package chess.domain.board;

import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Square> board;

    protected Board(Map<Position, Square> board) {
        this.board = board;
    }

    public Square findByPosition(Position position) {
       return this.board.get(position);
    }

    public Map<Position, Square> board() {
        return Collections.unmodifiableMap(board);
    }
}
