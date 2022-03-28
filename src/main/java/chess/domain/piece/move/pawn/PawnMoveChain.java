package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;

public abstract class PawnMoveChain {

    protected final PawnSupport support;

    public PawnMoveChain(PawnSupport support) {
        this.support = support;
    }

    public abstract boolean move(Board board, Point from, Point to);
}
