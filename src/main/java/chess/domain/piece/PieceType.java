package chess.domain.piece;

import chess.domain.piece.strategy.DiagonalStrategy;
import chess.domain.piece.strategy.EmptyStrategy;
import chess.domain.piece.strategy.IntersectionStrategy;
import chess.domain.piece.strategy.KnightStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.OnlyForwardStrategy;
import chess.domain.piece.strategy.PawnAttackStrategy;
import chess.domain.piece.strategy.StraightStrategy;
import chess.domain.piece.strategy.UnionStrategy;
import chess.domain.piece.strategy.WithinOneStepStrategy;
import chess.domain.square.Square;

import java.util.List;

public enum PieceType {

    KING(new IntersectionStrategy(List.of(
            new UnionStrategy(List.of(new StraightStrategy(), new DiagonalStrategy())),
            new WithinOneStepStrategy()))),
    QUEEN(new UnionStrategy(List.of(new StraightStrategy(), new DiagonalStrategy()))),
    ROOK(new StraightStrategy()),
    BISHOP(new DiagonalStrategy()),
    KNIGHT(new KnightStrategy()),
    PAWN(new UnionStrategy(List.of(new OnlyForwardStrategy(), new PawnAttackStrategy()))),
    EMPTY(new EmptyStrategy()),
    ;

    private final MoveStrategy moveStrategy;

    PieceType(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public boolean canMove(final Square source, final Square target) {
        return moveStrategy.canMove(source, target);
    }
}
