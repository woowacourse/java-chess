package chess.domain.piece;

import chess.domain.strategy.QueenMoveStrategy;

public final class Queen extends Piece {
    private final Team team;
    private final String symbol;

    public Queen(Team team, String symbol) {
        super(new QueenMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
