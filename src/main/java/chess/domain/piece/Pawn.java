package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private final String symbol;

    public Pawn(Team team, String symbol, Position position) {
        super(position, new PawnMoveStrategy(team), team);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
