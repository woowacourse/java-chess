package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedKnight;

public final class Knight extends Piece {
    private static final String NAME = "n";
    private static final double SCORE = 2.5;

    public Knight(Color color) {
        super(color, NAME, SCORE, new StartedKnight());
    }
}
