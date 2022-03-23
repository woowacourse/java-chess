package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class King extends Piece {

    public King(final Player player) {
        super(player, Unit.King);
    }

    @Override
    protected List<Direction> directions() {
        return null;
    }
}
