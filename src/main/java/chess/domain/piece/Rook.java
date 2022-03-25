package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedRook;

public final class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(Color color) {
        super(color, NAME, new StartedRook());
    }

}
