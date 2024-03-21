package model.piece;

import java.util.Objects;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public abstract class Piece {

    protected final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }


    //TODO : 리턴값 자료구조 정하기
    public abstract Set<Position> getRoute(Moving moving);

    protected abstract boolean canMovable(Moving moving);

    public Camp getCamp() {
        return camp;
    }

    public boolean isSameCamp(final Camp target) {
        return camp == target;
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
