package chess.domain.piece;

import chess.domain.strategy.RookMoveStrategy;

public final class Rook extends Piece {
    private final Team team;
    private final String symbol;

    public Rook(Team team, String symbol) {
        super(new RookMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getScore() {
        return 5;
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
