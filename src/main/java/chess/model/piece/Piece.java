package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.position.Position;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
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

    public boolean isPawn() {
        return false;
    }

    public abstract double addTo(double score);

    public abstract Route findRoute(final Position source, final Position target);

    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Piece that = (Piece) obj;
        return this.team == that.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
