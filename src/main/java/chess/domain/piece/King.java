package chess.domain.piece;

import chess.domain.strategy.KingMoveStrategy;

public final class King extends Piece {
    public static final int KING_SCORE = 0;
    private final Team team;
    private final String symbol;

    public King(Team team, String symbol) {
        super(new KingMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return KING_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
