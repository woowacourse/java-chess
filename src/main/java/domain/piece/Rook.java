package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.Direction;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rook extends Piece {

    private Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    public static Rook makeBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook makeWhite() {
        return new Rook(Color.WHITE);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
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
        return !start.isSameLine(end);
    }
}
