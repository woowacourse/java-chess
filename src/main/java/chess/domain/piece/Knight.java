package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedKnight;

public final class Knight extends Piece {
    private static final String NAME = "n";

    public Knight(Color color) {
        super(color, NAME, new StartedKnight());
    }
}
