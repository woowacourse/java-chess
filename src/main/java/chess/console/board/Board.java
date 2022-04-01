package chess.console.board;

import chess.console.piece.Piece;
import java.util.Map;

public final class Board {
    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
