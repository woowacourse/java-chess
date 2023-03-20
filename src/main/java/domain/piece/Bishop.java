package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bishop extends Piece {

    private Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    public static Bishop makeBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop makeWhite() {
        return new Bishop(Color.WHITE);
    }

    private boolean isNotMovable(final Location start, final Location end) {
        return !start.isDiagonal(end);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        final Direction direction = Direction.find(start, end);
        if (direction.equals(Direction.ELSE) || isNotMovable(start, end)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final int totalCount = Math.max(
            Math.abs(start.getCol() - end.getCol()), Math.abs(start.getRow() - end.getRow()));
        return IntStream.range(1, totalCount + 1)
            .mapToObj(
                count -> Location.of(
                    start.getCol() + (direction.getColDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }
}
