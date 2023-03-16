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
        return start.isDiagonalDirection(nextPosition);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
