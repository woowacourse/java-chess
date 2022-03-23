package chess.domain.piece;

import chess.domain.position.Position;

public final class Rook extends Piece {
    private final Team team;
    private final String symbol;

    public Rook(Team team, String symbol, Position position) {
        super(position);
        this.team = team;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
