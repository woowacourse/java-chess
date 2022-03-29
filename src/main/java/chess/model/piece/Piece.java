package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.position.Position;

public abstract class Piece {

    protected final Team team;
    private final String symbol;

    protected Piece(final Team team, final String symbol) {
        this.team = team;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isOpponent(final Team team) {
        return !team.equals(NONE) && !team.equals(this.team);
    }

    public boolean isSame(final Team team) {
        return team.equals(this.team);
    }

    public boolean isSame(final Piece otherPiece) {
        return otherPiece.team.equals(this.team);
    }

    public boolean isKing() {
        return false;
    }

    public abstract double addTo(double score);

    public abstract Route findRoute(final Position source, final Position target);
}
