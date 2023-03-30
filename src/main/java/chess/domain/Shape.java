package chess.domain;

import chess.domain.strategy.PieceStrategy;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.king.KingStrategy;
import chess.domain.strategy.knight.KnightStrategy;
import chess.domain.strategy.pawn.PawnStrategy;
import chess.domain.strategy.queen.QueenStrategy;
import chess.domain.strategy.rook.RookStrategy;

public enum Shape {

    PAWN(1, new PawnStrategy()),
    KING(0, new KingStrategy()),
    QUEEN(9, new QueenStrategy()),
    ROOK(5, new RookStrategy()),
    BISHOP(3, new BishopStrategy()),
    KNIGHT(2.5, new KnightStrategy());

    private final Score score;
    private final PieceStrategy pieceStrategy;

    Shape(final double score, final PieceStrategy pieceStrategy) {
        this.score = Score.from(score);
        this.pieceStrategy = pieceStrategy;
    }

    public double getScore() {
        return score.getValue();
    }

    public PieceStrategy getPieceStrategy() {
        return pieceStrategy;
    }
}
