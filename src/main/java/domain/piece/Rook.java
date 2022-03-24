package domain.piece;

import static domain.utils.Direction.*;

import domain.utils.Direction;
import java.util.List;

public final class Rook extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = upDownLeftRightDirections();
    }

    public Rook(final Player player) {
        super(player, PieceSymbol.Rook);

    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
