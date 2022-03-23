package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class Knight extends Piece {

    public Knight(final Player player) {
        super(player, PieceSymbol.Knight);
    }

    @Override
    protected List<Direction> directions() {
        return null;
    }
}
