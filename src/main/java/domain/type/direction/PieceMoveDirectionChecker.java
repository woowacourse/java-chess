package domain.type.direction;

import domain.Location;

public final class PieceMoveDirectionChecker {

    private PieceMoveDirectionChecker() {
    }

    protected static boolean isRightUpDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && end.isRightThan(start);
    }

    protected static boolean isRightDownDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && end.isRightThan(start);
    }

    protected static boolean isLeftUpDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && start.isRightThan(end);
    }

    protected static boolean isLeftDownDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && start.isRightThan(end);
    }

    protected static boolean isRight(final Location start, final Location end) {
        return start.isSameRow(end) && end.isRightThan(start);
    }

    protected static boolean isLeft(final Location start, final Location end) {
        return start.isSameRow(end) && start.isRightThan(end);
    }

    protected static boolean isDown(final Location start, final Location end) {
        return start.isSameCol(end) && start.isHigherThan(end);
    }

    protected static boolean isUp(final Location start, final Location end) {
        return start.isSameCol(end) && end.isHigherThan(start);
    }

    protected static boolean isTwoUpAndLeft(final Location start, final Location end) {
        return end.getRow() - start.getRow() == 2 && end.getCol() - start.getCol() == -1;
    }

    protected static boolean isTwoUpAndRight(final Location start, final Location end) {
        return end.getRow() - start.getRow() == 2 && end.getCol() - start.getCol() == 1;
    }

    protected static boolean isTwoDownAndLeft(final Location start, final Location end) {
        return end.getRow() - start.getRow() == -2 && end.getCol() - start.getCol() == -1;
    }

    protected static boolean isTwoDownAndRight(final Location start, final Location end) {
        return end.getRow() - start.getRow() == -2 && end.getCol() - start.getCol() == 1;
    }

    protected static boolean isTwoRightAndUp(final Location start, final Location end) {
        return end.getRow() - start.getRow() == 1 && end.getCol() - start.getCol() == 2;
    }

    protected static boolean isTwoRightAndDown(final Location start, final Location end) {
        return end.getRow() - start.getRow() == -1 && end.getCol() - start.getCol() == 2;
    }

    protected static boolean isTwoLeftAndUp(final Location start, final Location end) {
        return end.getRow() - start.getRow() == 1 && end.getCol() - start.getCol() == -2;
    }

    protected static boolean isTwoLeftAndDown(final Location start, final Location end) {
        return end.getRow() - start.getRow() == -1 && end.getCol() - start.getCol() == -2;
    }
}
