package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    private static final List<Direction> movableDirections = List.of(
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST);

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return checkMovable(source, target, movableDirections, pieces);
    }
}
