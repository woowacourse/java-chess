package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedKing;

public final class King extends Piece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(Color color) {
        super(color, NAME, SCORE, new StartedKing());
    }
}
