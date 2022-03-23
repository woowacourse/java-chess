package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    private final Team team;
    private final String symbol;

    public Pawn(Team team, String symbol, Position position) {
        super(position);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
