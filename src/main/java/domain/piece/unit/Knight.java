package domain.piece.unit;

import static domain.utils.Direction.knightDirections;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.utils.Direction;
import java.util.List;

public final class Knight extends SpecificMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = knightDirections();
    }

    public Knight(final Team team) {
        super(new PieceInfo(team, PieceSymbol.Knight));
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
