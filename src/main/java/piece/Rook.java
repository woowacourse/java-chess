package piece;

import java.util.Set;
import model.Camp;
import point.Position;

public class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        return null;
    }

    @Override
    public boolean canMovable(Position currentPosition, Position nextPosition) {
        return false;
    }

    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "r";
        }
        return "R";
    }
}
