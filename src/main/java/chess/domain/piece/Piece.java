package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Locale;
import java.util.Objects;

public abstract class Piece {
    private final Team team;
    private final String name;

    public Piece(final Team team, final String initialName) {
        this.team = team;
        if (team == Team.BLACK) {
            name = initialName.toUpperCase();
            return;
        }
        name = initialName.toLowerCase(Locale.ROOT);
    }

    final protected boolean opposite(final Piece piece) {
        return this.team == piece.team.oppositeTeam();
    }

    final public boolean friendly(final Team team) {
        return this.team == team;
    }

    final public String name() {
        return name;
    }

    final public Team team() {
        return this.team;
    }

    public abstract boolean movable(final Position source, final Position target, final Piece piece);

    public abstract double score();

    public abstract boolean multipleMovable();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
