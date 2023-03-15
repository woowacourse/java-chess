package chess.cache;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private BoardCache() {
    }

    static {
        for (int column = 0; column < 8; column++) {
            addRow(column);
        }
    }

    private static void addRow(final int column) {
        for (int row = 0; row < 8; row++) {
            board.put(new Position(column, row), new Piece(PieceType.EMPTY, Color.EMPTY));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
