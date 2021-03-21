package chess.domain.state;

import chess.domain.piece.Pieces;

public class Finished extends Turn {
    public Finished(final Pieces pieces) {
        super(pieces);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
