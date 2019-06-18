package chess.domain;

import java.util.Objects;

public class Pawn {
    private boolean life;

    public Pawn() {
        this.life = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pawn pawn = (Pawn) o;
        return life == pawn.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
