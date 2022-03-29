package domain.piece;

import domain.Player;
import domain.directions.Direction;
import java.util.List;

public class Bishop extends MovableRangePiece {

    private final List<Direction> directions;

    public Bishop(final Player player) {
        super(player, PieceSymbol.BISHOP);
        this.directions = List.of(
            Direction.NORTHEAST,
            Direction.NORTHWEST,
            Direction.SOUTHEAST,
            Direction.SOUTHWEST
        );
    }

    @Override
    protected List<Direction> getDirections() {
        return directions;
    }
}
