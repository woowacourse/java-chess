package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Locale;
import java.util.Objects;

public abstract class Piece {

    private final Team team;
    private final String name;

    public Piece(final Team team, final String initialName) {
        this.team = team;
        if (team.isBlackTeam()) {
            name = initialName.toUpperCase();
            return;
        }
        name = initialName.toLowerCase(Locale.ROOT);
    }

    protected boolean isOpponent(final Piece piece) {
        return this.team.isOppositeTeam(piece.team);
    }

    public boolean isSameTeam(final Team team) {
        return this.team.isSameTeam(team);
    }

    public String name() {
        return name;
    }

    public Team team() {
        return this.team;
    }

    public abstract boolean canMove(final Position source, final Position target,
        final Piece piece);

    public abstract boolean hasMiddlePath();

    public abstract double score();

    public abstract boolean isPawn();

    public abstract boolean isBlank();

    public abstract boolean isKing();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
