package chess.domain.piece;

import chess.domain.strategy.QueenMoveStrategy;

public final class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;
    private final String symbol;

    public Queen(Team team, String symbol) {
        super(new QueenMoveStrategy(), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
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
