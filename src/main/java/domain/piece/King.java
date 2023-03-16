package domain.piece;

import domain.position.Position;

import java.util.List;

public final class King extends Piece {

    public King(Color color) {
        super(PieceName.KING, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) &&
                isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        return start.isCrossDirection(nextPosition) || start.isDiagonalDirection(nextPosition);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return distance == 1;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
