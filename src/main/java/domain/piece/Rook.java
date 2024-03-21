package domain.piece;

import domain.Camp;
import domain.ChessVector;
import domain.Square;

import java.util.Objects;

public class Rook extends Piece {

    public Rook(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isHorizontalOrVertical();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Rook piece)) {
            return false;
        }
        return this.camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, Rook.class);
    }
}
