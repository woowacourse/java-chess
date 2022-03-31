package chess.domain.piece;

import chess.domain.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    public static final int PAWN_SCORE = 1;
    private final String symbol;

    public Pawn(Team team, String symbol) {
        super(new PawnMoveStrategy(), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
