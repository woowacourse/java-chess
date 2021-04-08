package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;

public final class Ready extends WinnerBlank {

    public Ready() {
        this(Color.WHITE);
    }

    public Ready(final Color color) {
        this(color, new Pieces());
    }

    public Ready(final Color color, final Pieces pieces) {
        super(color, pieces);
    }

    public State init() {
        pieces.init();
        return new Play(color, pieces);
    }
}
