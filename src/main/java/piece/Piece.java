package piece;

import java.util.Objects;
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
