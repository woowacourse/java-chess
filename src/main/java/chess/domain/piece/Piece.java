package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.Objects;

public abstract class Piece {
    protected final Camp camp;
    protected final Role role;

    public Piece(final Camp camp, final Role role) {
        this.camp = camp;
        this.role = role;
    }

    public boolean isMovable(final Square source, final Square target, final Move move) {
        throw new UnsupportedOperationException();
    }
    
    public boolean isSameRole(final Role role) {
        return this.role == role;
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp == camp;
    }

    public Camp getCamp() {
        return camp;
    }

    public Role getRole() {
        return role;
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
        return camp == piece.camp && role == piece.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, role);
    }
}
