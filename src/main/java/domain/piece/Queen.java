package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import domain.type.PieceType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.PieceView;

public final class Queen extends Piece {

    private Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    public static Queen makeBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen makeWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        final Direction direction = Direction.find(start, end);
        if (direction.equals(Direction.ELSE) || isNotMovable(start, end)) {
            throw new IllegalArgumentException(PieceView.findSign(this) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        final int totalMove = Math.max(
            Math.abs(start.getCol() - end.getCol()), Math.abs(start.getRow() - end.getRow()));
        return IntStream.range(1, totalMove + 1)
            .mapToObj(
                count -> Location.of(
                    start.getCol() + (direction.getColDiff() * count),
                    start.getRow() + (direction.getRowDiff()) * count))
            .collect(Collectors.toList());
    }

    private boolean isNotMovable(final Location start, final Location end) {
        return !(start.isSameLine(end) || start.isDiagonal(end));
    }
}
