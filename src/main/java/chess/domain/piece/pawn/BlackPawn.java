package chess.domain.piece.pawn;

import chess.domain.piece.strategy.BlackPawnMovableStrategy;

public final class BlackPawn extends AbstractBlackPawn {

    public BlackPawn() {
        super(new BlackPawnMovableStrategy());
    }
}
