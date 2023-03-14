package chess.domain;

import java.util.Collections;
import java.util.List;

public class Board {
    private final List<Square> squares;

    public Board(List<Square> squares) {
        this.squares = squares;
    }

    public List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }
}
