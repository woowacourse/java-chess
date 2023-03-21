package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Knight extends Piece {

    public Knight(Color color) {
        super(PieceName.KNIGHT, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return isKnightMovablePosition(start.calculateRowGap(end), start.calculateColumnGap(end));
    }

    private boolean isKnightMovablePosition(int rowGap, int columnGap) {
        return Math.abs(rowGap) * Math.abs(columnGap) == 2;
    }
}
