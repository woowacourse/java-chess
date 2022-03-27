package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedPawn;

public final class Pawn extends Piece {
    private static final String NAME = "p";
    private static final double SCORE = 1;

    public Pawn(Color color) {
        super(color, NAME, SCORE, new StartedPawn(color.forward()));
    }

    public boolean isSame(Piece piece) {
        return this.equals(piece);
    }
}
