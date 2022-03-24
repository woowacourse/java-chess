package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class King extends CommonMovablePiece {

    public King(final Player player) {
        super(player, PieceSymbol.King);
    }

    @Override
    protected List<Direction> directions() {
        return null;
    }
}
