package chess.domain.piece;

import chess.domain.strategy.KnightMoveStrategy;

public final class Knight extends Piece {

    public static final double KNIGHT_SOCRE = 2.5;
    private final Team team;
    private final String symbol;

    public Knight(Team team, String symbol) {
        super(new KnightMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return KNIGHT_SOCRE;
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
