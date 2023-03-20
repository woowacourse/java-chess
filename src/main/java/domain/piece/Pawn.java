package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(PieceName.PAWN, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        if (isBlack() && start.isBlackPawnInitialRow()) {
            return isMovableNonInitialRowPawn(start, path) ||
                    path.contains(start.moveDown().moveDown()) && path.size() == 2;
        }
        if (isWhite() && start.isWhitePawnInitialRow()) {
            return isMovableNonInitialRowPawn(start, path) ||
                    path.contains(start.moveUp().moveUp()) && path.size() == 2;
        }
        return isMovableNonInitialRowPawn(start, path);
    }

    private boolean isMovableNonInitialRowPawn(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) && isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        if (isBlack()) {
            return start.moveDown() == nextPosition ||
                    start.moveDownRight() == nextPosition ||
                    start.moveDownLeft() == nextPosition;
        }
        return start.moveUp() == nextPosition ||
                start.moveUpRight() == nextPosition ||
                start.moveUpLeft() == nextPosition;
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return distance == 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
