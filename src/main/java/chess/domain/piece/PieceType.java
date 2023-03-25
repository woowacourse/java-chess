package chess.domain.piece;

import chess.domain.piece.strategy.DiagonalStrategy;
import chess.domain.piece.strategy.IntersectionStrategy;
import chess.domain.piece.strategy.KnightStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.NoneStrategy;
import chess.domain.piece.strategy.StraightStrategy;
import chess.domain.piece.strategy.UnionStrategy;
import chess.domain.piece.strategy.UnitStrategy;

public enum PieceType {
    KING(IntersectionStrategy.of(
            UnionStrategy.of(DiagonalStrategy.instance(), StraightStrategy.instance()),
            UnitStrategy.instance())),
    QUEEN(UnionStrategy.of(DiagonalStrategy.instance(), StraightStrategy.instance())),
    PAWN(NoneStrategy.instance()),
    ROOK(StraightStrategy.instance()),
    BISHOP(DiagonalStrategy.instance()),
    KNIGHT(KnightStrategy.instance()),
    ;

    private final MoveStrategy moveStrategy;

    PieceType(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
}
