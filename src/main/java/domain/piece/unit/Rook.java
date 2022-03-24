package domain.piece.unit;

import static domain.utils.Direction.*;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Rook extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = upDownLeftRightDirections();
    }

    public Rook(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Rook);

    }

    @Override
    public List<Direction> directions() {
        return directions;
    }
}
