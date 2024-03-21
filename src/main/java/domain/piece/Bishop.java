package domain.piece;

import domain.Camp;
import domain.Square;
import domain.SquareVector;
import java.util.Objects;

public class Bishop extends Piece {

    public Bishop(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final SquareVector squareVector = SquareVector.of(source, target);
        return squareVector.isDiagonal();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Bishop piece)) {
            return false;
        }
        return this.camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, Bishop.class);
    }
}
