package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class King extends SpecificLocationPiece {

    private static final List<Direction> DIRECTIONS = List.of(
        Direction.NORTHEAST,
        Direction.NORTHWEST,
        Direction.SOUTHEAST,
        Direction.SOUTHWEST,
        Direction.EAST,
        Direction.WEST,
        Direction.SOUTH,
        Direction.NORTH
    );

    public King(final Player player) {
        super(player, PieceSymbol.KING);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
