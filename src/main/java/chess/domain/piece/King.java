package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {
    private final String symbol;

    public King(final Team team, final String symbol) {
        super(team, new KingMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
