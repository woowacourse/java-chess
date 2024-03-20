package piece;

import java.util.Set;
import model.Camp;
import point.Position;

public class Knight extends Piece {

    public Knight(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        if (canMovable(currentPosition, nextPosition)) {
            return Set.of();
        }
        throw new IllegalArgumentException("이동 불가");
    }

    @Override
    protected boolean canMovable(Position currentPosition, Position nextPosition) {
        if (currentPosition.equals(nextPosition)) {
            return false;
        }
        int dRow = Math.abs(currentPosition.getRowIndex() - nextPosition.getRowIndex());
        int dColumn = Math.abs(currentPosition.getColumnIndex() - nextPosition.getColumnIndex());
        return dRow + dColumn == 3 && dRow != 0 && dColumn != 0;
    }

    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "n";
        }
        return "N";
    }
}
