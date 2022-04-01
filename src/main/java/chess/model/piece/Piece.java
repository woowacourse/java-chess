package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.position.Position;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public boolean isOpponentTeam(Team team) {
        return team != this.team && this.team != NONE;
    }

    public abstract boolean canMove(Position source, Position target, Map<Position, Piece> board);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

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
