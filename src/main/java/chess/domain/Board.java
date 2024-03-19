package chess.domain;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new BoardFactory().create();
    }
}
