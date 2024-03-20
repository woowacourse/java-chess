package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board initialize() {
        Map<Position, Piece> board = new HashMap<>();

        return new Board(board);
    }
}
