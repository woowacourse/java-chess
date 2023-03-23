package domain.piece;

import domain.position.Path;
import domain.position.Position;

public abstract class Pawn extends Piece {

    public Pawn(Color color) {
        super(PieceName.PAWN, color);
    }

    @Override
    protected final boolean isMovableDirection(Position start, Position nextPosition) {
        return isForwardOneStep(start, nextPosition) ||
                isRightDiagonalOneStep(start, nextPosition) ||
                isLeftDiagonalOneStep(start, nextPosition);
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return distance == 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    protected final boolean isMovableInitialRowPawn(Position start, Path path) {
        return isMovableNonInitialRowPawn(start, path) ||
                path.size() == 2 && isForwardTwoStep(start, path.getEndPosition());
    }

    protected final boolean isMovableNonInitialRowPawn(Position start, Path path) {
        return isMovableDirection(start, path.getFirstPosition()) && isMovableDistance(path.size());
    }

    protected abstract boolean isForwardOneStep(Position start, Position nextPosition);

    protected abstract boolean isRightDiagonalOneStep(Position start, Position nextPosition);

    protected abstract boolean isLeftDiagonalOneStep(Position start, Position nextPosition);

    protected abstract boolean isForwardTwoStep(Position start, Position nextPosition);
}
