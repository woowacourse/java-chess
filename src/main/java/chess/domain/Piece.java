package chess.domain;

import java.util.Objects;

public class Piece {
    private final Role role;
    private final Camp camp;

    public Piece(final Role role, final Camp camp) {
        this.role = role;
        this.camp = camp;
    }

    public Piece() {
        this.role = Role.EMPTY;
        this.camp = Camp.EMPTY;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return role == piece.role && camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, camp);
    }
}
