package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.PieceMove;
import java.util.Objects;

public abstract class Piece {

    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public boolean isBlack() {
        return camp == Camp.BLACK;
    }

    public boolean isSameCamp(Piece other) {
        return camp == other.camp;
    }

    public boolean isMismatchedCamp(Camp camp) {
        return this.camp != camp;
    }

    public abstract PieceMove getMovement(Position from, Position to);

    protected abstract boolean isPieceRule(Position from, Position to);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp);
    }
}
