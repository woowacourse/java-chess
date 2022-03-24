package domain.piece.unit;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Bishop extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = Direction.crossDirections();
    }

    public Bishop(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Bishop);
    }

    @Override
    public List<Direction> directions() {
        return directions;
    }
}
