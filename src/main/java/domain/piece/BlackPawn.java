package domain.piece;

import domain.position.Position;

import java.util.List;

public final class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        if (start.isBlackPawnInitialRow()) {
            return isMovableInitialRowPawn(start, path);
        }
        return isMovableNonInitialRowPawn(start, path);
    }

    @Override
    protected boolean isForwardOneStep(Position start, Position nextPosition) {
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
    protected boolean isForwardTwoStep(Position start, Position nextPosition) {
        return start.calculateRowGap(nextPosition) == -2 && start.calculateColumnGap(nextPosition) == 0;
    }
}
