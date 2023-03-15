package chess.domain;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece getPiece(final int x, final int y) {
        return board.get(Position.of(x, y));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
