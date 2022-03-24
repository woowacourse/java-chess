package chess.domain.piece.pawn;

import chess.domain.Color;
import chess.domain.piece.strategy.PawnMovableStrategy;

public abstract class AbstractBlackPawn extends Pawn {

    private static final Color BLACK_PAWN_COLOR = Color.BLACK;

    public AbstractBlackPawn(PawnMovableStrategy pawnMovableStrategy) {
        super(BLACK_PAWN_COLOR, pawnMovableStrategy);
    }
}
