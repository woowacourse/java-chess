package domain.piece;

import domain.Color;
import domain.Location;

public class Pawn extends Piece {

    private Pawn(final Color color) {
        super(color);
    }

    public static Pawn makeBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn makeWhite() {
        return new Pawn(Color.WHITE);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        if (color.equals(Color.BLACK)) {
            return isPossibleBlack(start, end);
        }
        return isPossibleWhite(start, end);
    }

    private boolean isPossibleBlack(final Location start, final Location end) {
        if (start.isSameCol(end)) {
            return isFirstBlackMove(start, end);
        }
        if (start.isDiagonal(end)) {
            return start.getRow() - end.getRow() == 1;
        }
        return false;
    }

    private static boolean isFirstBlackMove(final Location start, final Location end) {
        if (start.getRow() == 7) {
            return start.getRow() - end.getRow() == 2
                || start.getRow() - end.getRow() == 1;
        }
        return start.getRow() - end.getRow() == 1;
    }

    private boolean isPossibleWhite(final Location start, final Location end) {
        if (start.isSameCol(end)) {
            return isFirstWhiteMove(start, end);
        }
        if (start.isDiagonal(end)) {
            return start.getRow() - end.getRow() == -1;
        }
        return false;
    }

    private static boolean isFirstWhiteMove(final Location start, final Location end) {
        if (start.getRow() == 2) {
            return start.getRow() - end.getRow() == -2
                || start.getRow() - end.getRow() == -1;
        }
        return start.getRow() - end.getRow() == -1;
    }
}
