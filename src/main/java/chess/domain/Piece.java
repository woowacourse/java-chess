package chess.domain;

import java.util.Objects;

public abstract class Piece {
    private static final String NAME = "r";
    private boolean life;
    private final Team team;

    public Piece(Team team) {
        this.life = true;
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }

    public abstract String getName();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return life == piece.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
