package chess.domain;

import chess.cache.BoardCache;
import chess.cache.PieceCache;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(PieceCache.create());
        return new Board(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
