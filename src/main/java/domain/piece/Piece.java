package domain.piece;

import java.util.Objects;

public abstract class Piece implements Moveable {
    private final String name;
    private final Team team;

    Piece(final String name, final Team team) {
        this.name = name;
        this.team = team;
    }

    public boolean isBlack() {
        return team.equals(Team.BLACK);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
