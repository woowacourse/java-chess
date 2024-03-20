package piece;

import model.Camp;
import point.Position;

public abstract class Piece {


    protected final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    abstract void move(Position targetPosition);

    public Camp getCamp() {
        return camp;
    }
}
