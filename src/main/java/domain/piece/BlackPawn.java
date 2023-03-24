package domain.piece;

import domain.position.Path;
import domain.position.Position;

public final class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    public boolean isMovablePath(Position start, Path path) {
        if (start.isBlackPawnInitialRow()) {
            return isMovableInitialRowPawn(start, path);
        }
        return isMovableNonInitialRowPawn(start, path);
    }

    @Override
    public boolean isForwardOneStep(Position start, Position nextPosition) {
        return start.calculateRowGap(nextPosition) == -1 && start.calculateColumnGap(nextPosition) == 0;
    }

    @Override
    protected boolean isRightDiagonalOneStep(Position start, Position nextPosition) {
        return start.calculateRowGap(nextPosition) == -1 && start.calculateColumnGap(nextPosition) == 1;
    }

    @Override
    protected boolean isLeftDiagonalOneStep(Position start, Position nextPosition) {
        return start.calculateRowGap(nextPosition) == -1 && start.calculateColumnGap(nextPosition) == -1;
    }

    @Override
    protected boolean isForwardTwoStep(Position start, Position endPosition) {
        return start.calculateRowGap(endPosition) == -2 && start.calculateColumnGap(endPosition) == 0;
    }
}
