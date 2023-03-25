package chess.domain.piece;

import chess.strategy.*;

public enum Role {

    KING(0, new KingStrategy()),
    QUEEN(9, new QueenStrategy()),
    ROOK(5, new RockStrategy()),
    BISHOP(3, new BishopStrategy()),
    KNIGHT(2.5, new KnightStrategy()),
    PAWN(1, new PawnStrategy()),
    EMPTY(0, new EmptyStrategy());

    private final double score;
    private final MoveStrategy moveStrategy;

    Role(double score, MoveStrategy moveStrategy) {
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public double getScore() {
        return score;
    }
}
