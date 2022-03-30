package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Queen extends MovableRangePiece {

    private final List<Direction> directions;

    public Queen(final Player player) {
        super(player, PieceSymbol.QUEEN);
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
    protected List<Direction> getDirections() {
        return directions;
    }
}
