package chess.domain;

import java.util.Objects;

public class Bishop {
    private boolean life;

    public Bishop() {
        this.life = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bishop rook = (Bishop) o;
        return life == rook.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
