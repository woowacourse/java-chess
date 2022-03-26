package domain.piece.unit;

import static domain.utils.Direction.allDirections;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.utils.Direction;
import java.util.List;

public final class Queen extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = allDirections();
    }

    public Queen(final Team team) {
        super(new PieceInfo(team, PieceSymbol.Queen));
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
