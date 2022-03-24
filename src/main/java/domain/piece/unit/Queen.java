package domain.piece.unit;

import static domain.utils.Direction.allDirections;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Queen extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = allDirections();
    }

    public Queen(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Queen);
    }

    @Override
    public List<Direction> directions() {
        return directions;
    }
}
