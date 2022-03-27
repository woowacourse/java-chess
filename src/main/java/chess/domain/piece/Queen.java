package chess.domain.piece;

import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends Piece{
    private final String symbol;

    public Queen(final Team team, final String symbol) {
        super(team, new QueenMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
