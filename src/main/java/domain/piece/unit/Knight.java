package domain.piece.unit;

import static domain.utils.Direction.knightDirections;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Knight extends CommonMovablePiece {
    private static final List<Direction> directions;

    static {
        directions = Direction.knightDirections();
    }
    public Knight(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Knight);
    }

    @Override
    public List<Direction> directions() {
        return directions;
    }
}
