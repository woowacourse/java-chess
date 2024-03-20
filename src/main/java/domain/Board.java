package domain;

import domain.piece.Piece;
import domain.piece.info.Position;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }


    public Map<Position, Piece> squares() {
        return squares;
    }
}
