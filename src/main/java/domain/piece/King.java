package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class King extends SpecificLocationPiece {

    private final List<Direction> directions;

    public King(final Player player) {
        super(player, PieceSymbol.KING);
        this.directions = List.of(
            Direction.NORTHEAST,
            Direction.NORTHWEST,
            Direction.SOUTHEAST,
            Direction.SOUTHWEST,
            Direction.EAST,
            Direction.WEST,
            Direction.SOUTH,
            Direction.NORTH
        );
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    protected List<Direction> getDirections() {
        return directions;
    }
}
