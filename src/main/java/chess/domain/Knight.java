package chess.domain;

import java.util.Objects;

public class Knight {
    private boolean life;

    public Knight() {
        this.life = true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Knight rook = (Knight) o;
        return life == rook.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
