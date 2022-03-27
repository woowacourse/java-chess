package chess.domain.piece;

import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {
    private final String symbol;

    public Rook(final Team team, final String symbol) {
        super(team, new RookMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
