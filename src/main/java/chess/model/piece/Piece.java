package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;

public abstract class Piece {

    protected final Team team;
    private final String symbol;


    protected Piece(Team team, String symbol) {
        this.team = team;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isSame(Team team) {
        return team.equals(this.team);
    }

    public boolean isSame(Piece otherPiece) {
        return otherPiece.team.equals(this.team);
    }

    public boolean isKing() {
        return false;
    }

    public abstract double addTo(double score);

    public boolean isPawn() {
        return false;
    }

    public Route findRoute(Position source, Position target) {
        return null;
    }
}
