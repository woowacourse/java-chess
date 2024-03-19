package chess.domain;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> values;

    public Board(Map<Position, Piece> values) {
        this.values = values;
    }
}
