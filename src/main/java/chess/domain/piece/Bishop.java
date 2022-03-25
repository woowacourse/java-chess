package chess.domain.piece;

import chess.domain.strategy.BishopMoveStrategy;

public final class Bishop extends Piece {
    private final String symbol;

    public Bishop(Team team, String symbol) {
        super(new BishopMoveStrategy(), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
