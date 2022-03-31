package chess.domain.piece;

import chess.domain.strategy.BishopMoveStrategy;

public final class Bishop extends Piece {
    public static final int BISHOP_SCORE = 3;
    private final String symbol;

    public Bishop(Team team, String symbol) {
        super(new BishopMoveStrategy(), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
