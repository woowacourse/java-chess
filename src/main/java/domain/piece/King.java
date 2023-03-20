package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class King extends Piece {

    private King(final Color color) {
        super(color, PieceType.KING);
    }

    public static King makeBlack() {
        return new King(Color.BLACK);
    }

    public static King makeWhite() {
        return new King(Color.WHITE);
    }

    boolean isNotMovable(final Location start, final Location end) {
        if (start.isSameCol(end)) {
            return Math.abs(start.getRow() - end.getRow()) != 1;
        }
        if (start.isSameRow(end)) {
            return Math.abs(start.getColumn() - end.getColumn()) != 1;
        }
        if (start.isDiagonal(end)) {
            return Math.abs(start.getColumn() - end.getColumn()) != 1;
        }
        return false;
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        final Direction direction = Direction.find(start, end);
        if (direction.equals(Direction.ELSE) || isNotMovable(start, end)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final int totalCount = Math.max(
            Math.abs(start.getColumn() - end.getColumn()), Math.abs(start.getRow() - end.getRow()));
        return IntStream.range(1, totalCount + 1)
            .mapToObj(
                count -> Location.of(
                    start.getColumn() + (direction.getColumnDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }
}
