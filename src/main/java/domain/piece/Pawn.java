package domain.piece;

import domain.utils.Direction;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(final Player player) {
        super(player, PieceSymbol.Pawn);
    }

    @Override
    protected List<Direction> directions() {
        return null;
    }
}
