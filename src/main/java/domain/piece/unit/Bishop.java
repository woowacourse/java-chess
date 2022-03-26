package domain.piece.unit;

import static domain.utils.Direction.*;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.utils.Direction;
import java.util.List;

public final class Bishop extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = crossDirections();
    }

    public Bishop(final Team team) {
        super(new PieceInfo(team, PieceSymbol.Bishop));
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
