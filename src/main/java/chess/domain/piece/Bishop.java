package chess.domain.piece;

import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends Piece {
    private final String symbol;

    public Bishop(final Team team, final String symbol) {
        super(team, new BishopMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
