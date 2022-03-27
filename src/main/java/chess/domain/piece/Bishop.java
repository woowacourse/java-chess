package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedBishop;

public final class Bishop extends Piece {
    private static final String NAME = "b";
    private static final double SCORE = 3;

    public Bishop(Color color) {
        super(color, NAME, SCORE, new StartedBishop());
    }
}
