package piece;

import java.util.Objects;
import java.util.Set;
import model.Camp;
import point.Position;

public abstract class Piece {

    protected final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    public abstract void move(Position targetPosition);

    //TODO : 리턴값 자료구조 정하기
    public abstract Set<Position> getRoute(Position currentPosition, Position nextPosition);

    protected abstract boolean canMovable(Position currentPosition, Position nextPosition);

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
