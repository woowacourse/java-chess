package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.PieceView;

public class Pawn extends Piece {

    private Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    public static Pawn makeBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn makeWhite() {
        return new Pawn(Color.WHITE);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException(PieceView.findSign(this) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final Direction direction = Direction.find(start, end);
        final int totalCount = Math.max(
            Math.abs(start.getCol() - end.getCol()), Math.abs(start.getRow() - end.getRow()));
        return IntStream.range(1, totalCount + 1)
            .mapToObj(
                count -> Location.of(
                    start.getCol() + (direction.getColDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }

    private boolean isNotMovable(final Location start, final Location end) {
        if (color.equals(Color.BLACK)) {
            return !isPossibleBlack(start, end);
        }
        return !isPossibleWhite(start, end);
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
        if (start.getRow() == 6) {
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
        if (start.getRow() == 1) {
            return start.getRow() - end.getRow() == -2
                || start.getRow() - end.getRow() == -1;
        }
        return start.getRow() - end.getRow() == -1;
    }
}
