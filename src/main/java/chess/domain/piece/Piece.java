package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Move;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.Objects;

public abstract class Piece {
    private final Camp camp;
    private final Square position;

    protected Piece(final Camp camp, final Square position) {
        this.camp = camp;
        this.position = position;
    }

    protected Piece(final Camp camp) {
        this.camp = camp;
        this.position = new Square(File.EMPTY, Rank.EMPTY);
    }

    public boolean isMovable(final Square target, final Move move, final boolean isPathBlocked) {
        throw new UnsupportedOperationException();
    }

    protected Square position() {
        return position;
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp == camp;
    }

    public Camp camp() {
        return camp;
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
        return Objects.hash(camp, position);
    }
}
