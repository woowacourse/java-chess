package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;

public class Knight extends Piece {
    private final String symbol;

    public Knight(final Team team, final String symbol) {
        super(team, new KingMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
