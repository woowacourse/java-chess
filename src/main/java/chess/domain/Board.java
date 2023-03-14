package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Board {
    Map<Square, Piece> board;

    public Board() {
        this.board = new LinkedHashMap<>();
    }
}
