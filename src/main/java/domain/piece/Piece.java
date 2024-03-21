package domain.piece;

import domain.Camp;
import domain.Square;

public abstract class Piece {

    protected final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    public abstract boolean canMove(Square source, Square target);

    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }

    public abstract PieceType getPieceType();

    public Camp getCamp() {
        return camp;
    }
}
