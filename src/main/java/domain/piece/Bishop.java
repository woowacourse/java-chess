package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class Bishop extends Piece {

    private static final List<Direction> directions;

    static {
        directions = Direction.crossDirection();
    }

    public Bishop(final Player player) {
        super(player, PieceSymbol.Bishop);
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
