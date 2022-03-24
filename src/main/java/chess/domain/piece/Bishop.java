package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedBishop;

public final class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(Color color) {
        super(color, NAME, new StartedBishop());
    }
}
