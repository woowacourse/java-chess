package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean containsPosition(Position position) {
        return board.containsKey(position);
    }

    public Piece pieceAt(Position position) {
        return board.get(position);
    }
}
