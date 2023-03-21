package domain.piece;

import domain.position.Direction;
import domain.position.Position;

import java.util.List;

public final class Bishop extends Piece {

    private static final List<Direction> BISHOP_MOVABLE_DIRECTIONS = List.of(Direction.DIAGONAL);

    public Bishop(Color color) {
        super(PieceName.BISHOP, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return BISHOP_MOVABLE_DIRECTIONS.contains(Direction.of(start, end));
    }
}
