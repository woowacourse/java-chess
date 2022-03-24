package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.PawnMovableStrategy;
import chess.domain.piece.strategy.WhitePawnFirstMovableStrategy;

public abstract class Pawn extends Piece {

    private static final String PAWN_NAME = "P";

    public Pawn(Color color) {
        super(color, PAWN_NAME, new WhitePawnFirstMovableStrategy());
    }

    public Pawn(Color color, PawnMovableStrategy pawnMovableStrategy) {
        super(color, PAWN_NAME, pawnMovableStrategy);
    }
}
