package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.PieceState;
import chess.domain.piece.state.started.MovedPawn;
import chess.domain.piece.state.started.StartedPawn;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final double SCORE = 1;

    public Pawn(Color color) {
        super(color, NAME, SCORE, new StartedPawn(color.forward()));
    }

    private Pawn(Color color, PieceState state) {
        super(color, NAME, SCORE, state);
    }

    public static Piece MovedOf(Color color) {
        return new Pawn(color, new MovedPawn(color.forward()));
    }

    public boolean isSame(Piece piece) {
        return this.equals(piece);
    }
}
