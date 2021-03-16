package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    protected Board(Map<Position, Piece> board) {
        this.board = board;
    }
}
