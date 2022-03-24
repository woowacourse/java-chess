package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.QueenMoveStrategy;

public final class Queen extends Piece {
    private final Team team;
    private final String symbol;

    public Queen(Team team, String symbol, Position position) {
        super(position, new QueenMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
