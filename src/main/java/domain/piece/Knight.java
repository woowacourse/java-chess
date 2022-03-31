package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Knight extends SpecificLocationPiece {

    private static final List<Direction> DIRECTIONS = List.of(
        Direction.NORTHEAST_NORTH,
        Direction.NORTHWEST_NORTH,
        Direction.NORTHWEST_WEST,
        Direction.SOUTHWEST_WEST,
        Direction.NORTHEAST_EAST,
        Direction.SOUTHEAST_EAST,
        Direction.SOUTHEAST_SOUTH,
        Direction.SOUTHWEST_SOUTH
    );

    public Knight(final Player player) {
        super(player, PieceSymbol.KNIGHT);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
