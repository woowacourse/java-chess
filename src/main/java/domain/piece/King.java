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
        return isMovableCrossDirection(start, nextPosition) || isMovableDiagonalDirection(start, nextPosition);
    }

    private boolean isMovableCrossDirection(Position start, Position nextPosition) {
        if (start.moveDown().equals(nextPosition) ||
                start.moveUp().equals(nextPosition) ||
                start.moveRight().equals(nextPosition) ||
                start.moveLeft().equals(nextPosition)) {
            return true;
        }
        return false;
    }

    private boolean isMovableDiagonalDirection(Position start, Position nextPosition) {
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
        return distance == 1;
    }
}
