package chess.domain.piece;

import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends Piece{
    private final String symbol;

    public Pawn(final Team team, final String symbol) {
        super(team, new PawnMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
