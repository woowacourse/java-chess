package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Square> board;

    protected Board(Map<Position, Square> board) {
        this.board = board;
    }

    public Piece findByPosition(Position position) {
       return this.board.get(position).getPiece();
    }

    public Map<Position, Square> board() {
        return Collections.unmodifiableMap(board);
    }
}
