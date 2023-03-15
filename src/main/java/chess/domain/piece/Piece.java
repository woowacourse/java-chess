package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Move;
import chess.domain.Square;
import java.util.Objects;

public abstract class Piece {
    protected final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    public boolean isMovable(final Square source, final Square target, final Move move) {
        throw new UnsupportedOperationException();
    }

    public boolean isMovable(final Square source, final Square target, final KnightMove move) {
        throw new UnsupportedOperationException();
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
        return camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp);
    }
}
