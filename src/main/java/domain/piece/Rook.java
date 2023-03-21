package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> ROOK_MOVABLE_DIRECTIONS = List.of(Direction.CROSS);

    public Rook(Color color) {
        super(PieceName.ROOK, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return ROOK_MOVABLE_DIRECTIONS.contains(Direction.of(start, end));
    }
}
