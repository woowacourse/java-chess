package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Map;

public class King extends Piece {
    private static final List<Direction> movableDirections = List.of(
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST,
            Direction.NORTH_EAST,
            Direction.NORTH_WEST,
            Direction.SOUTH_EAST,
            Direction.SOUTH_WEST);

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        for (final Direction movableDirection : movableDirections) {
            if (source.next(movableDirection).equals(target)) {
                return true;
            }
        }
        return false;
    }
}
