package domain.piece;

import domain.Color;
import domain.Direction;
import domain.Location;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class King extends Piece {

    private King(final Color color) {
        super(color);
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
            return Math.abs(start.getCol() - end.getCol()) != 1;
        }
        if (start.isDiagonal(end)) {
            return Math.abs(start.getCol() - end.getCol()) != 1;
        }
        return false;
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
}
