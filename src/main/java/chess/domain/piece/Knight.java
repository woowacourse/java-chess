package chess.domain.piece;

import chess.domain.strategy.KnightMoveStrategy;

public final class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;
    private final String symbol;

    public Knight(Team team, String symbol) {
        super(new KnightMoveStrategy(), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
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
