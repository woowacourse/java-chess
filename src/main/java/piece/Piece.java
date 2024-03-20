package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public abstract class Piece {

    protected final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    public abstract void move(Position targetPosition);

    public abstract boolean canMovable(Position currentPosition, Position nextPosition); //TODO 이동 위치에 어떤 기물이 있는지도 파악해야함

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
