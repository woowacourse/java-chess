package chess.domain.piece.move.pawn;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public abstract class PawnMoveChain {

    protected final PawnSupport support;

    public PawnMoveChain(PawnSupport support) {
        this.support = support;
    }

    public abstract boolean move(Route route, EmptyPoints emptyPoints);
}
