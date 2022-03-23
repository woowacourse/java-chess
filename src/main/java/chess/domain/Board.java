package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
