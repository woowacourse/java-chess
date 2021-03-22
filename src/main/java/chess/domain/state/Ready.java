package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;

public class Ready extends WinnerBlank {

    public Ready() {
        this(Color.WHITE);
    }

    public Ready(Color color) {
        this(color, new Pieces());
    }

    public Ready(Color color, Pieces pieces) {
        super(color, pieces);
    }

    public State init() {
        pieces.init();
        return new Play(color, pieces);
    }
}
