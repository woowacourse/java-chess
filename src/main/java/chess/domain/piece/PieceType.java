package chess.domain.piece;

import chess.domain.game.Score;
import chess.domain.piece.strategy.DiagonalStrategy;
import chess.domain.piece.strategy.IntersectionStrategy;
import chess.domain.piece.strategy.KnightStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.NoneStrategy;
import chess.domain.piece.strategy.StraightStrategy;
import chess.domain.piece.strategy.UnionStrategy;
import chess.domain.piece.strategy.UnitStrategy;

public enum PieceType {
    KING(0, IntersectionStrategy.of(
            UnionStrategy.of(DiagonalStrategy.instance(), StraightStrategy.instance()),
            UnitStrategy.instance())),
    QUEEN(9, UnionStrategy.of(DiagonalStrategy.instance(), StraightStrategy.instance())),
    ROOK(5, StraightStrategy.instance()),
    BISHOP(3, DiagonalStrategy.instance()),
    KNIGHT(2.5, KnightStrategy.instance()),
    PAWN(1, NoneStrategy.instance()),
    ;

    private final double score;
    private final MoveStrategy moveStrategy;

    PieceType(double score, MoveStrategy moveStrategy) {
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public Score getScore() {
        return Score.valueOf(score);
    }
}
