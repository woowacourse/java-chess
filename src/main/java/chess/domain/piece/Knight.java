package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.KnightMoveStrategy;

public final class Knight extends Piece {

    private final Team team;
    private final String symbol;

    public Knight(Team team, String symbol, Position position) {
        super(position, new KnightMoveStrategy(), team);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
