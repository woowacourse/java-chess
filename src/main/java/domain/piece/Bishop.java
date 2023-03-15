package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Bishop extends Piece {

    public Bishop(Color color) {
        super(PieceName.BISHOP, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) &&
                isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        if (start.moveUpRight().equals(nextPosition) ||
                start.moveUpLeft().equals(nextPosition) ||
                start.moveDownRight().equals(nextPosition) ||
                start.moveDownLeft().equals(nextPosition)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return true;
    }
}
