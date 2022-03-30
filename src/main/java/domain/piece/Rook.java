package domain.piece;

import domain.Player;
import domain.direction.Direction;
import java.util.List;

public class Rook extends MovableRangePiece {

    private final List<Direction> directions;

    public Rook(final Player player) {
        super(player, PieceSymbol.ROOK);
        this.directions = List.of(
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
