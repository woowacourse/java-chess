package domain.piece.unit;

import static domain.utils.Direction.*;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Bishop extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = crossDirections();
    }

    public Bishop(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Bishop);
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
