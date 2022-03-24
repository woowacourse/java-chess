package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedNight;

public final class Night extends Piece {
    private static final String NAME = "n";

    public Night(Color color) {
        super(color, NAME, new StartedNight());
    }
}
