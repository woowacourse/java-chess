package chess.domain;

import java.util.Objects;

public class King {
    private boolean life;

    public King() {
        this.life = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final King rook = (King) o;
        return life == rook.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
