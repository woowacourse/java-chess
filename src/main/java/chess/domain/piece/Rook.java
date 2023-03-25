package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public final class Rook extends Piece {

    private static final Set<Direction> ROOK_MOVABLE_DIRECTIONS = Set.of(Direction.CROSS);

    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        return ROOK_MOVABLE_DIRECTIONS.contains(Direction.of(start, end));
    }
}
