package domain.piece;

import domain.position.Direction;
import domain.position.Path;
import domain.position.Position;

import java.util.Set;

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

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    protected Set<Direction> getMovableDirections() {
        throw new UnsupportedOperationException("[ERROR] Pawn 객체에서는 지원하지 않는 기능입니다.");
    }

    protected final boolean isMovableInitialRowPawn(Position start, Path path) {
        return isMovableNonInitialRowPawn(start, path) ||
                path.size() == 2 && isForwardTwoStep(start, path.getEndPosition());
    }

    protected final boolean isMovableNonInitialRowPawn(Position start, Path path) {
        return isMovableDirection(start, path.getFirstPosition()) && isMovableDistance(path.size());
    }

    public abstract boolean isForwardOneStep(Position start, Position nextPosition);

    protected abstract boolean isRightDiagonalOneStep(Position start, Position nextPosition);

    protected abstract boolean isLeftDiagonalOneStep(Position start, Position nextPosition);

    protected abstract boolean isForwardTwoStep(Position start, Position endPosition);
}
