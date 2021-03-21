package chess.domain.state;

import chess.domain.piece.Pieces;

public class Running extends Turn {
    public Running(final Pieces pieces) {
        super(pieces);
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
