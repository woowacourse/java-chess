package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedPawn;

public final class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(Color color) {
        super(color, NAME, new StartedPawn());
    }
}
