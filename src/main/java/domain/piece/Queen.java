package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class Queen extends Piece {

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen);
    }

    @Override
    protected List<Direction> directions() {
        return null;
    }
}
