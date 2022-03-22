package chess;

import chess.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> value;

    public Board(final Map<Position, Piece> value) {
        this.value = Map.copyOf(value);
    }

    public static Board create() {
        return new Board(new HashMap<>());
    }
}
