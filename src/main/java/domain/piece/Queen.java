package domain.piece;

import static domain.utils.Direction.allDirections;

import domain.utils.Direction;
import java.util.List;

public final class Queen extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = allDirections();
    }

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen);
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
