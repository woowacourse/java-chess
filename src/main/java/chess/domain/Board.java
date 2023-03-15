package chess.domain;

import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }
}
