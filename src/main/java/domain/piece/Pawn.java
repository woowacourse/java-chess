package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private boolean isNotMovable(final Location start, final Location end) {
        return Math.abs(start.getRow() - end.getRow()) > 2;
    }
}
