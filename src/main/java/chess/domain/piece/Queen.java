package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public final class Queen extends Piece {

    private static final List<Direction> QUEEN_MOVABLE_DIRECTIONS = List.of(Direction.CROSS, Direction.DIAGONAL);

    public Queen(Color color) {
        super(PieceName.QUEEN, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return QUEEN_MOVABLE_DIRECTIONS.contains(Direction.of(start, end));
    }
}
