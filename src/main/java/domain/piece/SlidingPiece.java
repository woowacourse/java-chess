package domain.piece;

import domain.Location;
import domain.Section;
import domain.type.Color;
import domain.type.Direction;
import domain.type.PieceType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    @Override
    public List<Location> searchPath(final Section start, final Section end) {
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        checkMovable(direction, start, end);
        final int totalCount = getTotalMovement(start, end);
        return getPath(start, direction, totalCount);
    }

    private void checkMovable(final Direction direction, final Section start, final Section end) {
        if (direction.equals(Direction.ELSE) || isNotMovable(start, end)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
    }

    private int getTotalMovement(final Section start, final Section end) {
        return Math.max(
            Math.abs(start.getColumn() - end.getColumn()),
            Math.abs(start.getRow() - end.getRow()));
    }

    private List<Location> getPath(final Section start, final Direction direction, final int totalCount) {
        return IntStream.range(1, totalCount + 1)
            .mapToObj(
                count -> Location.of(
                    start.getColumn() + (direction.getColumnDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }
}
