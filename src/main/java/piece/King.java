package piece;

import java.util.Set;
import model.Camp;
import point.Position;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp);
    }


    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        if (!canMovable(currentPosition, nextPosition)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return Set.of();
    }

    @Override
    protected boolean canMovable(Position currentPosition, Position nextPosition) {
        if (currentPosition.equals(nextPosition)) {
            return false;
        }
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        return Math.abs(nextRow - currentRow) <= 1 && Math.abs(nextColumn - currentColumn) <= 1;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "k";
        }
        return "K";
    }
}
