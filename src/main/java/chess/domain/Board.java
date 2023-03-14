package chess.domain;

import java.util.List;

public class Board {
    private final List<Square> squares;

    public Board(List<Square> squares) {
        this.squares = squares;
    }
}
