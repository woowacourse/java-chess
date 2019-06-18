package chess.domain;

import java.util.Objects;

public class Rook {
    private boolean life;

    public Rook() {
        this.life = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Rook rook = (Rook) o;
        return life == rook.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
