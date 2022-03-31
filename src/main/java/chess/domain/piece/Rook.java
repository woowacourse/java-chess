package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedRook;

public final class Rook extends Piece {
    private static final String NAME = "r";
    private static final double SCORE = 5;

    public Rook(Color color) {
        super(color, NAME, SCORE, new StartedRook());
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
