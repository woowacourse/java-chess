package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public final class King extends Piece {

    private static final Set<Direction> KING_MOVABLE_DIRECTIONS = Set.of(Direction.CROSS, Direction.DIAGONAL);
    private static final int KING_ROUTE_SIZE = 2;

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return KING_MOVABLE_DIRECTIONS.contains(Direction.of(start, end)) && routeFromStartToEnd.size() == KING_ROUTE_SIZE;
    }
}
