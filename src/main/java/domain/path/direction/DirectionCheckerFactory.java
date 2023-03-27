package domain.path.direction;

import domain.path.PieceMove;
import domain.path.location.Location;

public final class DirectionCheckerFactory {

    private DirectionCheckerFactory() {
    }

    static boolean isRightUp(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && end.isRightThan(start);
    }

    static boolean isRightDown(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && end.isRightThan(start);
    }

    static boolean isLeftUp(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && start.isRightThan(end);
    }

    static boolean isLeftDown(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && start.isRightThan(end);
    }

    static boolean isRight(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isSameRow(end) && end.isRightThan(start);
    }

    static boolean isLeft(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isSameRow(end) && start.isRightThan(end);
    }

    static boolean isDown(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isSameCol(end) && start.isHigherThan(end);
    }

    static boolean isUp(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return start.isSameCol(end) && end.isHigherThan(start);
    }

    static boolean isTwoUpAndLeft(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == 2 && end.getCol() - start.getCol() == -1;
    }

    static boolean isTwoUpAndRight(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == 2 && end.getCol() - start.getCol() == 1;
    }

    static boolean isTwoDownAndLeft(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == -2 && end.getCol() - start.getCol() == -1;
    }

    static boolean isTwoDownAndRight(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == -2 && end.getCol() - start.getCol() == 1;
    }

    static boolean isTwoRightAndUp(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == 1 && end.getCol() - start.getCol() == 2;
    }

    static boolean isTwoRightAndDown(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == -1 && end.getCol() - start.getCol() == 2;
    }

    static boolean isTwoLeftAndUp(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == 1 && end.getCol() - start.getCol() == -2;
    }

    static boolean isTwoLeftAndDown(final PieceMove pieceMove) {
        final Location start = pieceMove.getStart();
        final Location end = pieceMove.getEnd();
        return end.getRow() - start.getRow() == -1 && end.getCol() - start.getCol() == -2;
    }
}
